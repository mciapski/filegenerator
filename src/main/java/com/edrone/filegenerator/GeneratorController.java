package com.edrone.filegenerator;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GeneratorController {

    @Autowired
    GeneratorRepository generatorRepository;
    @Autowired
    FileGenerationService fileGenerationService;

    @Autowired
    JobScheduler jobScheduler;

    @PostMapping("/api/generate_file")
    public List<GeneratedString> startGenerationJob(@RequestBody FileGenerationRequest request) throws NotEnoughCharsException {
        return fileGenerationService.saveAllGeneratedStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength());
    }
    @PostMapping("/api/generate_file/job")
    public List<GeneratedString> runJob(@RequestBody FileGenerationRequest request){
        jobScheduler.enqueue(() ->fileGenerationService.saveAllGeneratedStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength()));
        return fileGenerationService.saveAllGeneratedStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength());
    }

    @GetMapping("/api/generate_file")
    public List<GeneratedString> getGeneratedStringsFromDB() {
        return generatorRepository.findAll();
    }

    @PostMapping("/api/generate_file/send_to_file")
    public String saveToFile(@RequestBody FileGenerationRequest request){
        List<GeneratedString> generatedStringList=fileGenerationService.returnGeneratedListOfStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength());
        fileGenerationService.saveGeneratedStringsToFile(generatedStringList);
        return "Done";
    }

}

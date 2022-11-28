package com.edrone.filegenerator;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        return fileGenerationService.generateRandomStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength());
    }
    @PostMapping("/api/generate_file/job")
    public List<GeneratedString> runJob(@RequestBody FileGenerationRequest request){
        jobScheduler.enqueue(() ->fileGenerationService.generateRandomStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength()));
        return fileGenerationService.generateRandomStrings(
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
        String inputCharSequence = request.alphabet();
        Integer requestedQuantityOfWords = request.wordCount();
        Integer minLength = request.minLength();
        Integer maxLength = request.maxLength();
        fileGenerationService.checkIfCharactersQuantityEnough(inputCharSequence, requestedQuantityOfWords, minLength, maxLength);
        Set<GeneratedString> setOfGeneratedStrings = new TreeSet<>();
        List<GeneratedString> generatedStringList1 = new ArrayList<>();

        int sizeOfCharSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence(inputCharSequence).length();
        String charSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence(inputCharSequence);

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= requestedQuantityOfWords; i++) {
            int randomLengthBetweenMinAndMax = random.nextInt(minLength, maxLength + 1);
            for (int j = 0; j < randomLengthBetweenMinAndMax; j++) {
                int index = random.nextInt(sizeOfCharSequenceWithoutDuplicates);
                char randomChar = charSequenceWithoutDuplicates.charAt(index);
                sb.append(randomChar);
            }
            String newString = sb.toString();
            setOfGeneratedStrings.add(new GeneratedString(i, newString));
            sb = new StringBuilder();
        }
        generatedStringList1.addAll(setOfGeneratedStrings);
        List<GeneratedString> generatedStringList= generatedStringList1;
        fileGenerationService.saveGeneratedStringsToFile(generatedStringList);
        return "Done";
    }

}

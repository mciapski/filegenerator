package com.edrone.filegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class GeneratorController {

    @Autowired
    GeneratorRepository generatorRepository;
    @Autowired
    FileGenerationService fileGenerationService;

    @PostMapping("/api/generate_file")
    public Set<GeneratedString> startGenerationJob(@RequestBody FileGenerationRequest request) throws NotEnoughCharsException {
        boolean wordQuantityStatus = fileGenerationService.checkIfCharactersQuantityEnough(request.alphabet(), request.wordCount(), request.minLength(), request.maxLength());
        Set<GeneratedString> setoToSaveInDB = new HashSet<>();
        if (!wordQuantityStatus) {
            throw new NotEnoughCharsException("Za mała ilość znaków");
        } else {
            setoToSaveInDB = fileGenerationService.returnGeneratedSetOfStrings(request.alphabet(), request.wordCount(), request.minLength(), request.maxLength());
            generatorRepository.saveAll(setoToSaveInDB);
            return fileGenerationService.returnGeneratedSetOfStrings(request.alphabet(), request.wordCount(), request.minLength(), request.maxLength());
        }

    }
}

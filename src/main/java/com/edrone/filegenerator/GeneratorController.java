package com.edrone.filegenerator;

import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class GeneratorController {
    public boolean checkIfCharactersQuantityEnough(String inputCharSequence,
                                                   Integer requestedQuantityOfWords,
                                                   Integer minLength,
                                                   Integer maxLength) {
        boolean state=false;
        Set<Character> inputCharSequenceWithoutDuplicates = inputCharSequence.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toSet());
        // For minLength = 2, maxLength=4, inputCharSequence=3
        // possibleQuantityOfWords = 2^2 + 2^3
        int baseOfPower = inputCharSequenceWithoutDuplicates.size();
        int indexOfPower;
        int possibleQuantityOfWords = 0;
        for (int i = minLength; i <= maxLength; i++) {
            indexOfPower = i;
            possibleQuantityOfWords += Math.pow(baseOfPower, indexOfPower);
        }

        return requestedQuantityOfWords <= possibleQuantityOfWords;
    }

    @PostMapping("/api/generate_file")
    public ResponseEntity startGenerationJob(@RequestParam String inputCharSequence,
                                             @RequestParam Integer requestedQuantityOfWords,
                                             @RequestParam Integer minLength,
                                             @RequestParam Integer maxLength) throws Exception {

        if(!checkIfCharactersQuantityEnough(inputCharSequence, requestedQuantityOfWords, minLength, maxLength)){
            throw new NotEnoughCharsException("Za mała ilość znaków");
        }
        return ResponseEntity.ok().build();
    }


}

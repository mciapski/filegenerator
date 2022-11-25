package com.edrone.filegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileGenerationService {

//    @Autowired
//    GeneratorRepository generatorRepository;
    public boolean checkIfCharactersQuantityEnough(String inputCharSequence,
                                                   Integer requestedQuantityOfWords,
                                                   Integer minLength,
                                                   Integer maxLength) {
        boolean state = false;
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
}

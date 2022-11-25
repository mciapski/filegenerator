package com.edrone.filegenerator;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileGenerationService {

    //    @Autowired
//    GeneratorRepository generatorRepository;
    public boolean checkIfCharactersQuantityEnough(String inputCharSequence,
                                                   Integer requestedQuantityOfWords,
                                                   Integer minLength,
                                                   Integer maxLength) {

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

    public Set<GeneratedString> returnGeneratedSetOfStrings(String inputCharSequence,
                                                            Integer requestedQuantityOfWords,
                                                            Integer minLength,
                                                            Integer maxLength) {

        Set<GeneratedString> setOfGeneratedStrings = new TreeSet<>();
        int sizeOfCharSequenceWithoutDuplicates = removeDuplicatesFromCharSequence(inputCharSequence).length();
        String charSequenceWithoutDuplicates = removeDuplicatesFromCharSequence(inputCharSequence);

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < requestedQuantityOfWords; i++) {
            int randomLengthBetweenMinAndMax = random.nextInt(minLength, maxLength + 1);
            for (int j = 0; j < randomLengthBetweenMinAndMax; j++) {
                int index = random.nextInt(sizeOfCharSequenceWithoutDuplicates);
                char randomChar = charSequenceWithoutDuplicates.charAt(index);
                sb.append(randomChar);
            }
            String newString = sb.toString();
            setOfGeneratedStrings.add(new GeneratedString(i,newString));
            sb=new StringBuilder();
        }

        return setOfGeneratedStrings;
    }

    public String removeDuplicatesFromCharSequence(String inputCharSequence) {
        Set<Character> inputCharSequenceWithoutDuplicates = inputCharSequence.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toSet());
        String inputCharSequenceWithoutDuplicatesAsString = "";
        for (Character character : inputCharSequenceWithoutDuplicates) {
            inputCharSequenceWithoutDuplicatesAsString += character;
        }
        return inputCharSequenceWithoutDuplicatesAsString;
    }
}

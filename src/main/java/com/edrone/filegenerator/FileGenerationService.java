package com.edrone.filegenerator;

import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileGenerationService {

    @Autowired
    GeneratorRepository generatorRepository;


    public boolean checkIfCharactersQuantityEnough(String inputCharSequence,
                                                   Integer requestedQuantityOfWords,
                                                   Integer minLength,
                                                   Integer maxLength) {
        // For minLength = 2, maxLength=4, inputCharSequence=3
        // possibleQuantityOfWords = 2^2 + 2^3
        int baseOfPower = removeDuplicatesFromCharSequence(inputCharSequence).length();
        int indexOfPower;
        int possibleQuantityOfWords = 0;
        for (int i = minLength; i <= maxLength; i++) {
            indexOfPower = i;
            possibleQuantityOfWords += Math.pow(baseOfPower, indexOfPower);
        }
        if (requestedQuantityOfWords <= possibleQuantityOfWords) {
            return true;
        }
        throw new NotEnoughCharsException("Za mała ilość znaków");
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

    public List<GeneratedString> returnGeneratedListOfStrings(String inputCharSequence,
                                                              Integer requestedQuantityOfWords,
                                                              Integer minLength,
                                                              Integer maxLength) {
        checkIfCharactersQuantityEnough(inputCharSequence,requestedQuantityOfWords,minLength,maxLength);
        Set<GeneratedString> setOfGeneratedStrings = new TreeSet<>();
        List<GeneratedString> generatedStringList = new ArrayList<>();

        int sizeOfCharSequenceWithoutDuplicates = removeDuplicatesFromCharSequence(inputCharSequence).length();
        String charSequenceWithoutDuplicates = removeDuplicatesFromCharSequence(inputCharSequence);

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
        generatedStringList.addAll(setOfGeneratedStrings);
        return generatedStringList;
    }

    @Job(name = "Saving all records to DB")
    public List<GeneratedString> saveAllGeneratedStrings(String inputCharSequence,
                                                         Integer requestedQuantityOfWords,
                                                         Integer minLength,
                                                         Integer maxLength){
        return generatorRepository.saveAll(returnGeneratedListOfStrings(
                inputCharSequence,
                requestedQuantityOfWords,
                minLength,
                maxLength));
    }

    public void saveGeneratedStringsToFile(List<GeneratedString> generatedStringList){
        try{
            File fileWithStrings = new File("C:/Users/mciap/OneDrive/Pulpit/fileWithStrings");
            if(!fileWithStrings.exists()){
                fileWithStrings.createNewFile();
            }else {
                FileOutputStream fileOutputStream = new FileOutputStream(fileWithStrings);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                for(int i=0; i<generatedStringList.size(); i++){
                    objectOutputStream.writeBytes(generatedStringList.get(i).getGeneratedString());
                }
                objectOutputStream.close();
                fileOutputStream.close();
            }
        }catch (IOException e){
            System.out.println(e);

        }
    }



}

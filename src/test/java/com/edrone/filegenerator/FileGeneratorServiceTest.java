package com.edrone.filegenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FileGeneratorServiceTest {
    FileGenerationService fileGenerationService;
    @BeforeEach
    void setUp(){
        fileGenerationService = new FileGenerationService();
    }





    @Test
    void removeDuplicatesFromCharSequenceTest() {
        //when
        String expectedSecond = "am";
        String result = fileGenerationService.removeDuplicatesFromCharSequence("mama");
        //then
        assertThat(result).isEqualTo(expectedSecond);
    }

    @Test
    void returnThreeGeneratedStringsTest() {
        // given
        fileGenerationService.checkIfCharactersQuantityEnough("ola", 3, 2, 5);
        Set<GeneratedString> setOfGeneratedStrings = new TreeSet<>();
        List<GeneratedString> generatedStringList = new ArrayList<>();

        int sizeOfCharSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence("ola").length();
        String charSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence("ola");

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 3; i++) {
            int randomLengthBetweenMinAndMax = random.nextInt(2, 5 + 1);
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
        List<GeneratedString> listOfGeneratedStrings = generatedStringList;
        // when
        int expected = 3;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void returnMilionGeneratedStringsTest() {
        // given
        fileGenerationService.checkIfCharactersQuantityEnough("michalijustynaasdasdasdrhrthtrt13578012", 1000000, 2, 5);
        Set<GeneratedString> setOfGeneratedStrings = new TreeSet<>();
        List<GeneratedString> generatedStringList = new ArrayList<>();

        int sizeOfCharSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence("michalijustynaasdasdasdrhrthtrt13578012").length();
        String charSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence("michalijustynaasdasdasdrhrthtrt13578012");

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 1000000; i++) {
            int randomLengthBetweenMinAndMax = random.nextInt(2, 5 + 1);
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
        List<GeneratedString> listOfGeneratedStrings = generatedStringList;
        // when
        int expected = 1000000;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void returnTwoMilionsGeneratedStringsTest() {
        // given
        FileGenerationService fileGenerationService = new FileGenerationService();
        fileGenerationService.checkIfCharactersQuantityEnough("1234567890@#$%^&*()qwertyuiopasdfghjklzxcvbnm", 2000000, 2, 5);
        Set<GeneratedString> setOfGeneratedStrings = new TreeSet<>();
        List<GeneratedString> generatedStringList = new ArrayList<>();

        int sizeOfCharSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence("1234567890@#$%^&*()qwertyuiopasdfghjklzxcvbnm").length();
        String charSequenceWithoutDuplicates = fileGenerationService.removeDuplicatesFromCharSequence("1234567890@#$%^&*()qwertyuiopasdfghjklzxcvbnm");

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 2000000; i++) {
            int randomLengthBetweenMinAndMax = random.nextInt(2, 5 + 1);
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
        List<GeneratedString> listOfGeneratedStrings = generatedStringList;
        // when
        int expected = 2000000;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
}

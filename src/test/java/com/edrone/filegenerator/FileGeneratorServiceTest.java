package com.edrone.filegenerator;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileGeneratorServiceTest {



    @Test
    void removeDuplicatesFromCharSequenceTest() {
        //given
        FileGenerationService fileGenerationService = new FileGenerationService();
        //when
        String expectedSecond = "am";
        String result = fileGenerationService.removeDuplicatesFromCharSequence("mama");
        //then
        assertThat(result).isEqualTo(expectedSecond);
    }

    @Test
    void returnThreeGeneratedStringsTest() {
        // given
        FileGenerationService fileGenerationService = new FileGenerationService();
        List<GeneratedString> listOfGeneratedStrings = fileGenerationService
                .returnGeneratedListOfStrings("ola", 3, 2, 5);
        // when
        int expected = 3;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void returnMilionGeneratedStringsTest() {
        // given
        FileGenerationService fileGenerationService = new FileGenerationService();
        List<GeneratedString> listOfGeneratedStrings = fileGenerationService
                .returnGeneratedListOfStrings("michalijustynaasdasdasdrhrthtrt13578012", 1000000, 2, 5);
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
        List<GeneratedString> listOfGeneratedStrings = fileGenerationService
                .returnGeneratedListOfStrings("1234567890@#$%^&*()qwertyuiopasdfghjklzxcvbnm", 2000000, 2, 5);
        // when
        int expected = 2000000;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
}

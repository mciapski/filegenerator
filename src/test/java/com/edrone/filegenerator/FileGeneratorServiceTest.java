package com.edrone.filegenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        var request = new FileGenerationRequest("123456789@#$%^&*",3,2,10);
        List<GeneratedString> listOfGeneratedStrings = fileGenerationService.generateRandomStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength());
        // when
        int expected = 3;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void returnMilionGeneratedStringsTest() {
        // given
        var request = new FileGenerationRequest("123456789@#$%^&*",1000000,2,10);
        List<GeneratedString> listOfGeneratedStrings = fileGenerationService.generateRandomStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength());
        // when
        int expected = 1000000;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void returnTwoMilionsGeneratedStringsTest() {
        // given
        var request = new FileGenerationRequest("123456789@#$%^&*",2000000,2,10);
        List<GeneratedString> listOfGeneratedStrings = fileGenerationService.generateRandomStrings(
                request.alphabet(),
                request.wordCount(),
                request.minLength(),
                request.maxLength());
        // when
        int expected = 2000000;
        int result = listOfGeneratedStrings.size();

        // then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void saveSampleStringToFile () throws IOException {
        String str = "Hello";
        String fileName = "stringsFile";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);

        writer.close();
    }
}

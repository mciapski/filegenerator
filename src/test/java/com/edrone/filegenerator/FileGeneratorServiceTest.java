package com.edrone.filegenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

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
    void returnGeneratedSetOfStringsTest() {
        //given
        FileGenerationService fileGenerationService = new FileGenerationService();
        //when
        Set<GeneratedString> setOfGeneratedStrings = fileGenerationService
                .returnGeneratedSetOfStrings("ola", 3, 2, 5);
        System.out.println(setOfGeneratedStrings);

    }
}

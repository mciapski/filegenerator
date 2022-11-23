package com.edrone.filegenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
class GeneratorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void returnErrorForInvalidInput() throws Exception {
        // when
        var response = mockMvc.perform(post("/api/generate_file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    // Napisać teraz przypadek gdzie nie da sie wygenerować
    @Test
    public void returnErrorIfCharactersQuantityNotEnough() throws Exception {

        // when
        // For minLength = 2, maxLength=4, inputCharSequence=3
        // possibleQuantityOfWords = 2^2 + 2^3 = 36 so it should throw an exception
        mockMvc.perform(post("/api/generate_file")
                        .param("inputCharSequence", "Ola")
                        .param("requestedQuantityOfWords","100")
                        .param("minLength", "2")
                        .param("maxLength", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotEnoughCharsException))
                .andExpect(result -> assertEquals("Za mała ilość znaków", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

}
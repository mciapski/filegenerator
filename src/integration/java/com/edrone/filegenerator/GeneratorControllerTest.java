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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void returnErrorIfGeneratingImpossible() throws Exception {
        // given
        // when
        mockMvc.perform(post("/api/generate_file")
                        .param("wordInput", "Ola")
                        .param("requestedQuantityOfWords","100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotEnoughCharsException))
                .andExpect(result -> assertEquals("Za mała ilość znaków", Objects.requireNonNull(result.getResolvedException()).getMessage()));
        // then
    }

}
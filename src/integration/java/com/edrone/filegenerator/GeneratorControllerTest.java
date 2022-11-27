package com.edrone.filegenerator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.WebApplicationContext;


import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GeneratorController.class)
@ContextConfiguration(classes = {GeneratorController.class,ExceptionAdvice.class})
@AutoConfigureMockMvc
class GeneratorControllerTest {

    @MockBean
    GeneratorController generatorController;


    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private Gson gson;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void generatorControllerIsNotNull() {
        assertThat(generatorController).isNotNull();
    }

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

    @Test
    public void returnErrorIfCharactersQuantityNotEnough() throws Exception {
        var request = new FileGenerationRequest("Ola", 200, 2, 3);
        // given
        // For minLength = 2, maxLength=4, inputCharSequence=3
        // possibleQuantityOfWords = 2^2 + 2^3 = 36, so it should throw an exception

        //when
        when(generatorController.startGenerationJob(request)).thenThrow(new NotEnoughCharsException("Za mała ilość znaków"));
//        System.out.println(generatorController.startGenerationJob(request));
        var response = mockMvc.perform(post("/api/generate_file")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotEnoughCharsException))
                .andExpect(result -> assertEquals("Za mała ilość znaków", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void returnListOfGeneratedWords() throws Exception {

        // given
        var request = new FileGenerationRequest("Ola", 100, 2, 5);
        // when
        when(generatorController.startGenerationJob(request)).thenReturn(List.of(new GeneratedString(1, "Dramat")));
        var response = mockMvc.perform(post("/api/generate_file")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn()
                .getResponse();
        // then
        System.out.println(response.getContentAsString());
        List<GeneratedString> result = gson.fromJson(response.getContentAsString(), new TypeToken<List<GeneratedString>>() {
        }.getType());
        assertThat(result).isEqualTo(List.of(new GeneratedString(1, "Dramat")));
    }




}
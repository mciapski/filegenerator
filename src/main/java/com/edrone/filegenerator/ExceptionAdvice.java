package com.edrone.filegenerator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotEnoughCharsException.class)
    public String handle(NotEnoughCharsException e) {
        return "Za mała ilość znaków";
    }
}

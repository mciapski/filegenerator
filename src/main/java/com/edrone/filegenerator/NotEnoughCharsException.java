package com.edrone.filegenerator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotEnoughCharsException extends RuntimeException {
    public NotEnoughCharsException(String message) {
        super(message);
    }
}

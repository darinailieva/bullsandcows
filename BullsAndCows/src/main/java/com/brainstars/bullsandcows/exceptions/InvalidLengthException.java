package com.brainstars.bullsandcows.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidLengthException extends RuntimeException {
    private final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

    public InvalidLengthException() {
        super("The length of the guessed number should be equal to 4.");
    }
}

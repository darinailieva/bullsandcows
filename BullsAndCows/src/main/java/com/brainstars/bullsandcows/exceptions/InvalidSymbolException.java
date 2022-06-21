package com.brainstars.bullsandcows.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidSymbolException extends RuntimeException {
    private final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

    public InvalidSymbolException() {
        super("The guessed number should contains only digits.");
    }
}

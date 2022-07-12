package com.brainstars.bullsandcows.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class InvalidSymbolException extends RuntimeException {
    private final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

    public InvalidSymbolException() {
        super("The guessed number should contain only digits.");
    }
}

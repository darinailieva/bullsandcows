package com.brainstars.bullsandcows.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateEntityException extends RuntimeException {
    private final HttpStatus status = HttpStatus.CONFLICT;

    public DuplicateEntityException(String type, String attribute, String value) {
        super(String.format("%s with %s '%s' already exists.", type, attribute, value));
    }
}


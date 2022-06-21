package com.brainstars.bullsandcows.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(String type, String attribute, String value) {
        super(String.format("%s with %s '%s' does not exist.", type, attribute, value));
    }
}

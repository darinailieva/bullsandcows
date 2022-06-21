package com.brainstars.bullsandcows.controllers.errors;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private Integer code;
    private HttpStatus status;
    private String message;
}

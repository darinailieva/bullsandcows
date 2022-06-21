package com.brainstars.bullsandcows.controllers.errors;

import com.brainstars.bullsandcows.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEntityException(DuplicateEntityException e) {
        HttpStatus status = e.getStatus();
        int code = status.value();
        String message = e.getMessage();
        return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
    }

    @ExceptionHandler(InvalidLengthException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLengthException(InvalidLengthException e) {
        HttpStatus status = e.getStatus();
        int code = status.value();
        String message = e.getMessage();
        return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
    }

    @ExceptionHandler(InvalidSymbolException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSymbolException(InvalidSymbolException e) {
        HttpStatus status = e.getStatus();
        int code = status.value();
        String message = e.getMessage();
        return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
    }

    @ExceptionHandler(DuplicateDigitException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateDigitException(DuplicateDigitException e) {
        HttpStatus status = e.getStatus();
        int code = status.value();
        String message = e.getMessage();
        return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus status = e.getStatus();
        int code = status.value();
        String message = e.getMessage();
        return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
    }
}

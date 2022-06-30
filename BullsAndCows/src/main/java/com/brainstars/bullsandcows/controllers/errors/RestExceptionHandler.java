package com.brainstars.bullsandcows.controllers.errors;

import com.brainstars.bullsandcows.exceptions.DuplicateDigitException;
import com.brainstars.bullsandcows.exceptions.DuplicateEntityException;
import com.brainstars.bullsandcows.exceptions.EntityNotFoundException;
import com.brainstars.bullsandcows.exceptions.InvalidLengthException;
import com.brainstars.bullsandcows.exceptions.InvalidSymbolException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(DuplicateEntityException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateEntityException(DuplicateEntityException exception) {
    HttpStatus status = exception.getStatus();
    int code = status.value();
    String message = exception.getMessage();
    return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
  }

  @ExceptionHandler(InvalidLengthException.class)
  public ResponseEntity<ErrorResponse> handleInvalidLengthException(InvalidLengthException exception) {
    HttpStatus status = exception.getStatus();
    int code = status.value();
    String message = exception.getMessage();
    return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
  }

  @ExceptionHandler(InvalidSymbolException.class)
  public ResponseEntity<ErrorResponse> handleInvalidSymbolException(InvalidSymbolException exception) {
    HttpStatus status = exception.getStatus();
    int code = status.value();
    String message = exception.getMessage();
    return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
  }

  @ExceptionHandler(DuplicateDigitException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateDigitException(DuplicateDigitException exception) {
    HttpStatus status = exception.getStatus();
    int code = status.value();
    String message = exception.getMessage();
    return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
    HttpStatus status = exception.getStatus();
    int code = status.value();
    String message = exception.getMessage();
    return new ResponseEntity<>(new ErrorResponse(code, status, message), status);
  }
}

package com.brainstars.bullsandcows.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class InvalidLengthException extends RuntimeException {
  private final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

  public InvalidLengthException() {
    super("The length of the guessed number should be equal to 4.");
  }
}

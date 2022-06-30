package com.brainstars.bullsandcows.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DuplicateDigitException extends RuntimeException {
  private final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

  public DuplicateDigitException() {
    super("The guessed number cannot contain duplicate digits.");
  }
}
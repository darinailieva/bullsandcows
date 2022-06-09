package com.brainstars.bullsandcows.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

/**
 * Custom exception when the submitted data is invalid.
 */

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@NoArgsConstructor
public class InvalidParameterException extends RuntimeException {
  public InvalidParameterException(String attribute) {
    super(String.format("%s can not have repeated digits and its length can not be bigger than 4.",
      attribute));
  }
}

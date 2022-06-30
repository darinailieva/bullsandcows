package com.brainstars.bullsandcows.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttemptResponse {
  private Integer bulls;
  private Integer cows;
  private String currentNumber;
}

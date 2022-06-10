package com.brainstars.bullsandcows.models.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGameResponse {
  private Integer gameId;
  private Integer timesPlayed;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  boolean isFinished;
}

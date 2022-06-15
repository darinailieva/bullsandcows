package com.brainstars.bullsandcows.models.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameResponse {
  private Integer gameId;
  private Boolean finished;
  List<AttemptResponse> attempts;
}

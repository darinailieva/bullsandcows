package com.brainstars.bullsandcows.models.dtos;

import lombok.Getter;

@Getter
public class BullsCowsCounter {
  private int bulls;
  private int cows;

  public void addBulls() {
    bulls++;
  }

  public void addCows() {
    cows++;
  }
}

package com.brainstars.bullsandcows.models.dtos;


public interface UsersResponse {
  String getUsername();

  Integer getNumberOfFinishedGames();

  Integer getBestTimesPlayed();

  Integer getBestTimeInMinutes();
}

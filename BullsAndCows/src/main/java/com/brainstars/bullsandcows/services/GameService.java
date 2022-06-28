package com.brainstars.bullsandcows.services;

import java.util.List;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.UsersResponse;

public interface GameService {
  Game startGame(String username);

  void guessNumber(Integer gameId, Attempt attempt);

  Game getById(Integer gameId);

  List<Game> getAllUserGames(String username);

  List<UsersResponse> findAllByMinTimesPlayedAndMinDateDifference();

}

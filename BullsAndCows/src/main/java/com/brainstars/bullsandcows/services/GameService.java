package com.brainstars.bullsandcows.services;

import java.security.Principal;
import java.util.List;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;

public interface GameService {
  Game startGame(Principal principal);

  void guessNumber(int gameId, Attempt attempt);

  Game getById(int gameId);

  List<Game> getAllUserGames(String username);

  List<Object[]> findAllByMinTimesPlayedAndMinDateDifference();

}

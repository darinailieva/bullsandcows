package com.brainstars.bullsandcows.services;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;

import java.security.Principal;
import java.util.List;

public interface GameService {
    Game startGame(Principal principal);

    void guessNumber(int gameId, Attempt attempt);

    List<Game> getAllGames();

    Game getById(int gameId);

    List<Game> getAllUserGames(String username);

    List<Object[]> findAllByMinTimesPlayedAndMinDateDifference();

}

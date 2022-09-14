package com.brainstars.bullsandcows.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.AttemptRequest;
import com.brainstars.bullsandcows.models.dtos.AttemptResponse;
import com.brainstars.bullsandcows.models.dtos.GameResponse;
import com.brainstars.bullsandcows.models.dtos.UserGameResponse;

public class GameMapper {
  public static Attempt convertToAttempt(AttemptRequest request) {
    Attempt attempt = new Attempt();
    if (Objects.nonNull(request)) {
      attempt.setCurrentNumber(request.getCurrentNumber());
    }
    return attempt;
  }

  public static List<UserGameResponse> convertToGameResponses(List<Game> userGames) {
    List<UserGameResponse> gamesResponses = new ArrayList<>();
    for (Game game : userGames) {
      UserGameResponse userGamesResponse = new UserGameResponse();
      userGamesResponse.setGameId(game.getGameId());
      userGamesResponse.setTimesPlayed(game.getTimesPlayed());
      userGamesResponse.setCreatedDate(game.getCreatedDate());
      userGamesResponse.setLastModifiedDate(game.getLastModifiedDate());
      userGamesResponse.setFinished(game.getFinished());
      gamesResponses.add(userGamesResponse);
    }
    return gamesResponses;
  }

  public static GameResponse convertToGameResponse(Game game) {
    GameResponse response = new GameResponse();
    if (Objects.nonNull(game)) {
      response.setGameId(game.getGameId());
      response.setFinished(game.getFinished());
      List<AttemptResponse> attempts = new ArrayList<>();
      for (int i = 0; i < game.getAttempts().size(); i++) {
        AttemptResponse attemptResponse = new AttemptResponse();
        attemptResponse.setBulls(game.getAttempts().get(i).getBulls());
        attemptResponse.setCows(game.getAttempts().get(i).getCows());
        attemptResponse.setCurrentNumber(game.getAttempts().get(i).getCurrentNumber());
        attempts.add(attemptResponse);
        response.setAttempts(attempts);
      }
    }
    return response;
  }
}

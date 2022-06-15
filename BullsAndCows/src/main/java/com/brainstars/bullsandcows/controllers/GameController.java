package com.brainstars.bullsandcows.controllers;

import static com.brainstars.bullsandcows.mappers.GameMapper.convertToAttempt;
import static com.brainstars.bullsandcows.mappers.GameMapper.convertToGameResponse;
import static com.brainstars.bullsandcows.mappers.GameMapper.convertToGameResponses;

import java.security.Principal;
import java.util.List;

import com.brainstars.bullsandcows.exceptions.InvalidParameterException;
import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.AttemptRequest;
import com.brainstars.bullsandcows.models.dtos.GameResponse;
import com.brainstars.bullsandcows.models.dtos.UserGameResponse;
import com.brainstars.bullsandcows.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameController {
  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/games")
  public ResponseEntity<List<UserGameResponse>> showAllUserGames(Principal principal) {
    List<Game> userGames = gameService.getAllUserGames(principal.getName());
    List<UserGameResponse> userGameResponses = convertToGameResponses(userGames);
    return new ResponseEntity<>(userGameResponses, HttpStatus.OK);
  }

  @PostMapping("/game")
  public ResponseEntity<Integer> startGame(@RequestBody String username) {
    Game game = gameService.startGame(username);
    return new ResponseEntity<>(game.getGameId(), HttpStatus.OK);
  }

  @GetMapping("/game/{gameId}")
  public ResponseEntity<GameResponse> showGame(@PathVariable int gameId) {
    Game game = gameService.getById(gameId);
    GameResponse response = convertToGameResponse(game);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/game/{gameId}")
  public ResponseEntity<GameResponse> guessNumber(@PathVariable int gameId,
    @RequestBody AttemptRequest request, BindingResult errors) {
    Game game = gameService.getById(gameId);
    GameResponse response = convertToGameResponse(game);
    InvalidParameterException exception = validateAttemptRequest(request);
    if (exception != null) {
      ObjectError error = new ObjectError("error", exception.getMessage());
      errors.addError(error);
    }
    if (errors.hasErrors()) {
      return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    Attempt attempt = convertToAttempt(request);
    gameService.guessNumber(gameId, attempt);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  private InvalidParameterException validateAttemptRequest(AttemptRequest request) {
    String currentNumber = request.getCurrentNumber();
    int length = currentNumber.length();
    if (length > 4) {
      return new InvalidParameterException("Guessed number");
    }
    if (!currentNumber.matches("^[0-9]*$")) {
      return new InvalidParameterException("Guessed number");
    }

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        if (i != j && currentNumber.charAt(i) == currentNumber.charAt(j)) {
          return new InvalidParameterException("Guessed number");
        }
      }
    }
    return null;
  }
}

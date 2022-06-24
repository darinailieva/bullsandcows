package com.brainstars.bullsandcows.controllers;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.AttemptRequest;
import com.brainstars.bullsandcows.models.dtos.GameResponse;
import com.brainstars.bullsandcows.models.dtos.UserGameResponse;
import com.brainstars.bullsandcows.models.dtos.UsersResponse;
import com.brainstars.bullsandcows.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.brainstars.bullsandcows.mappers.GameMapper.*;

@RestController
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<UsersResponse>> showDashboardPage() {
        List<UsersResponse> usersResponses = gameService.findAllByMinTimesPlayedAndMinDateDifference();
        return new ResponseEntity<>(usersResponses, HttpStatus.OK);
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
    public ResponseEntity<GameResponse> getGame(@PathVariable Integer gameId) {
        Game game = gameService.getById(gameId);
        GameResponse response = convertToGameResponse(game);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/game/{gameId}")
    public ResponseEntity<GameResponse> guessNumber(@PathVariable Integer gameId, @RequestBody AttemptRequest request) {
        Game game = gameService.getById(gameId);
        GameResponse response = convertToGameResponse(game);
        Attempt attempt = convertToAttempt(request);
        gameService.guessNumber(gameId, attempt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

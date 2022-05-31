package com.brainstars.bullsandcows.controllers;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.AttemptRequest;
import com.brainstars.bullsandcows.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Principal;

import static com.brainstars.bullsandcows.mappers.GameMapper.convertToAttempt;

@Controller
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

//    @GetMapping
//    public String getStarted (Model model){
//        model.addAttribute("games", gameService.getAllGames());
//        return "game";
//    }

    @GetMapping
    public String startGame(Principal principal, Model model) {
        Game game = gameService.startGame(principal);
        model.addAttribute("game", game);
        return "game";
    }

    @GetMapping("{gameId}")
    public String showGame(@PathVariable int gameId, Model model) {
        Game game = gameService.getById(gameId);
        model.addAttribute("game", game);
        model.addAttribute("gameId", game.getGameId());
        model.addAttribute("timesPlayed", game.getTimesPlayed());
        model.addAttribute("attempts", game.getAttempts());
        model.addAttribute("createdDate", game.getCreatedDate());
        model.addAttribute("lastModifiedDate", game.getLastModifiedDate());
        return "game";
    }

    @PostMapping("{gameId}")
    public String guessNumber(@PathVariable int gameId, @ModelAttribute("currentAttemptRequest") AttemptRequest request) {
        validateAttemptRequest(request);
        Attempt attempt = convertToAttempt(request);
        gameService.guessNumber(gameId, attempt);
        return "redirect:/game/" + gameId;
    }

    private void validateAttemptRequest(AttemptRequest request) {
        String currentNumber = request.getCurrentNumber();
        int length = currentNumber.length();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j && currentNumber.charAt(i) == currentNumber.charAt(j)) {
                    //TODO make better exception
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
                }
            }
        }
    }
}
package com.brainstars.bullsandcows.controllers;

import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String startGame(Principal principal) {
        gameService.startGame(principal);
        return "index";
    }
}

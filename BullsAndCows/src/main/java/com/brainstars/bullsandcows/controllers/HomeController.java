package com.brainstars.bullsandcows.controllers;

import com.brainstars.bullsandcows.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private GameService gameService;

    @Autowired
    public HomeController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String showRegistrationPage(Model model) {
//        List<UsersResponse> usersResponses = convertToUsersResponse(gameService.findAllByMinTimesPlayedAndMinDateDifference());
        //        model.addAttribute("users", usersResponses);
        return "index";
    }

    @GetMapping("/rules")
    public String showRulesPage() {
        return "how-to-play";
    }
}

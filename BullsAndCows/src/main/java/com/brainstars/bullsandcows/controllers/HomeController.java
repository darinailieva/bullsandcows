package com.brainstars.bullsandcows.controllers;

import java.util.List;

import com.brainstars.bullsandcows.models.dtos.UsersResponse;
import com.brainstars.bullsandcows.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  private final GameService gameService;

  @Autowired
  public HomeController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/")
  public String showHomePage() {
    return "index";
  }

  @GetMapping("/dashboard")
  public ResponseEntity<List<UsersResponse>> showDashboardPage() {
    List<UsersResponse> usersResponses =
      gameService.findAllByMinTimesPlayedAndMinDateDifference();
    return new ResponseEntity<>(usersResponses, HttpStatus.OK);
  }

  @GetMapping("/rules")
  public String showRulesPage() {
    return "how-to-play";
  }
}

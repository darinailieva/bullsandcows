package com.brainstars.bullsandcows.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String showHomePage() {
    return "index";
  }

  @GetMapping("/rules")
  public String showRulesPage() {
    return "how-to-play";
  }

  @GetMapping("/register")
  public String showRegistrationPage() {
    return "register";
  }

  @GetMapping("/login")
  public String showLogin() {
    return "login";
  }
}

package com.brainstars.bullsandcows.controllers;

import static com.brainstars.bullsandcows.mappers.UserMapper.convertToUser;

import com.brainstars.bullsandcows.models.User;
import com.brainstars.bullsandcows.models.dtos.UserDTO;
import com.brainstars.bullsandcows.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller public class RegisterController {
  private final UserService userService;

  @Autowired public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register") public String showRegistrationPage() {
    return "register";
  }

  @PostMapping(path = "/register")
  public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
    User user = convertToUser(userDTO);
    userService.createUser(user);
    return ResponseEntity.ok("User successfully registered.");
  }
}

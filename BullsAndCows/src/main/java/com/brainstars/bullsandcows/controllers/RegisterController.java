package com.brainstars.bullsandcows.controllers;

import static com.brainstars.bullsandcows.mappers.UserMapper.convertToUser;

import com.brainstars.bullsandcows.models.User;
import com.brainstars.bullsandcows.models.dtos.UserDTO;
import com.brainstars.bullsandcows.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
  private final UserService userService;

  @Autowired public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/register")
  public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
    User user = convertToUser(userDTO);
    userService.createUser(user);
    return ResponseEntity.ok(String.format("User with username '%s' successfully registered.", user.getUsername()));
  }
}

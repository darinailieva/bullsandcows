package com.brainstars.bullsandcows.controllers;

import static com.brainstars.bullsandcows.mappers.UserMapper.convertToUser;

import com.brainstars.bullsandcows.exceptions.DuplicateEntityException;
import com.brainstars.bullsandcows.models.User;
import com.brainstars.bullsandcows.models.dtos.UserDTO;
import com.brainstars.bullsandcows.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
  private final UserService userService;

  @Autowired
  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String showRegistrationPage(Model model) {
    model.addAttribute("userDTO", new UserDTO());
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute("userDTO") UserDTO userDTO, BindingResult errors) {
    if (userService.userExists(userDTO.getUsername())) {
      ObjectError error = new ObjectError("error",
        new DuplicateEntityException("User", "username", userDTO.getUsername()).getMessage());
      errors.addError(error);
    }
    if (errors.hasErrors()) {
      return "register";
    }
    User user = convertToUser(userDTO);
    userService.createUser(user);
    return "login";
  }
}

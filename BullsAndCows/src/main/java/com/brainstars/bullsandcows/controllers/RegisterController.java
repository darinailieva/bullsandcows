package com.brainstars.bullsandcows.controllers;

import com.brainstars.bullsandcows.exceptions.DuplicateEntityException;
import com.brainstars.bullsandcows.models.User;
import com.brainstars.bullsandcows.models.dtos.UserDTO;
import com.brainstars.bullsandcows.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

import static com.brainstars.bullsandcows.mappers.UserMapper.convertToUser;

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
        User user = convertToUser(userDTO);
        if (userService.userExists(user)) {
            ObjectError error = new ObjectError("error", new DuplicateEntityException("User", "username", userDTO.getUsername()).getMessage());
            errors.addError(error);
        }
        if (errors.hasErrors()) {
            return "register";
        }
        userService.createUser(user);
        return "login";
    }
}

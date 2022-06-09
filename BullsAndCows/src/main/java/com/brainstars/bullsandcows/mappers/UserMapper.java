package com.brainstars.bullsandcows.mappers;

import java.util.Objects;

import com.brainstars.bullsandcows.models.User;
import com.brainstars.bullsandcows.models.dtos.UserDTO;

public class UserMapper {
  public static User convertToUser(UserDTO userDTO) {
    User user = new User();
    if (Objects.nonNull(userDTO)) {
      user.setUsername(userDTO.getUsername());
      user.setPassword(userDTO.getPassword());
    }
    return user;
  }
}

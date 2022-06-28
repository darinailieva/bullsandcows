package com.brainstars.bullsandcows.services;

import java.util.List;

import com.brainstars.bullsandcows.exceptions.DuplicateEntityException;
import com.brainstars.bullsandcows.models.User;
import com.brainstars.bullsandcows.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserDetailsManager userDetails;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserDetailsManager userDetails,
    PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userDetails = userDetails;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void createUser(User user) {
    if (userDetails.userExists(user.getUsername())) {
      throw new DuplicateEntityException("User", "username", user.getUsername());
    }

    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    user.setIsEnabled(true);
    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
    org.springframework.security.core.userdetails.User newUser =
      new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        authorities);
    userDetails.createUser(newUser);
    userRepository.save(user);
  }

}

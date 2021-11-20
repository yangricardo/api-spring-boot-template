package com.github.yangricardo.api_spring_boot.modules.auth.services;

import com.github.yangricardo.api_spring_boot.modules.auth.model.CreateUserDTO;
import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import com.github.yangricardo.api_spring_boot.modules.users.services.IUserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private IUserService userService;

  @Autowired
  PasswordEncoder passwordEncoder;

  public AuthenticationService(IUserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userService.findByUsername(username);
    if (user.isPresent()) {
      return user.get();
    }

    throw new UsernameNotFoundException("User not found");
  }

  public User createUser(CreateUserDTO createUserDTO) throws Exception {
    Optional<User> userFound = this.userService.findByEmail(createUserDTO.getEmail());
    if (userFound.isPresent()) {
      throw new Exception("Email alreary Taken");
    }
    User user = User.builder().username(createUserDTO.getUsername()).email(createUserDTO.getEmail())
        .password(passwordEncoder.encode(createUserDTO.getPassword())).build();
    return userService.create(user);
  }
}

package com.github.yangricardo.api_spring_boot.modules.users.services;

import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import java.util.Optional;

public interface IUserService {
  Optional<User> findById(Long id);

  Optional<User> findByUsername(String username);

  User create(User user);
}

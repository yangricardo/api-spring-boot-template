package com.github.yangricardo.api_spring_boot.modules.users.services;

import java.util.Optional;

import com.github.yangricardo.api_spring_boot.modules.users.model.User;

public interface IUserService {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User create(User user);

}

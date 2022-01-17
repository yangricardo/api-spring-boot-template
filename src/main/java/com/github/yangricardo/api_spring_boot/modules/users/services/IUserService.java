package com.github.yangricardo.api_spring_boot.modules.users.services;

import com.github.yangricardo.api_spring_boot.modules.users.model.Role;
import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import com.github.yangricardo.api_spring_boot.modules.users.repository.IRoleRepository;
import com.github.yangricardo.api_spring_boot.modules.users.repository.IUserRepository;

import java.util.Optional;

public interface IUserService {

  IUserRepository getUserRepository();

  IRoleRepository getRoleRepository();

  Optional<User> findById(Long id);

  Optional<User> findByUsername(String username);

  User create(User user);

  Role createRoleIfNotFound(String roleName);
}

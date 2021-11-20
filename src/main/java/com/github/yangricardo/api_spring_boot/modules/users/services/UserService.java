package com.github.yangricardo.api_spring_boot.modules.users.services;

import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import com.github.yangricardo.api_spring_boot.modules.users.repository.IUserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findById(Long id) {
    return this.userRepository.findById(id);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  @Override
  public User create(User user) {
    return this.userRepository.save(user);
  }
}

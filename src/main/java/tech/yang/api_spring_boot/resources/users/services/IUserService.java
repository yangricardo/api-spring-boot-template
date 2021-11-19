package tech.yang.api_spring_boot.resources.users.services;

import java.util.Optional;

import tech.yang.api_spring_boot.resources.users.model.User;

public interface IUserService {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User create(User user);

}

package com.github.yangricardo.api_spring_boot.modules.users.repository;

import java.util.Optional;

import com.github.yangricardo.api_spring_boot.modules.users.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}

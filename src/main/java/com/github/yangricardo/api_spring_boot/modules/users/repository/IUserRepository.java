package com.github.yangricardo.api_spring_boot.modules.users.repository;

import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IUserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUsername(String username);
}

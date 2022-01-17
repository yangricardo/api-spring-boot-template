package com.github.yangricardo.api_spring_boot.modules.users.repository;

import java.util.Optional;

import com.github.yangricardo.api_spring_boot.modules.users.model.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IRoleRepository extends CrudRepository<Role, Long> {
  Optional<Role> findByRole(String role);
}

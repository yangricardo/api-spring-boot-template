package com.github.yangricardo.api_spring_boot.modules.users.services;

import com.github.yangricardo.api_spring_boot.modules.users.model.Role;
import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import com.github.yangricardo.api_spring_boot.modules.users.repository.IRoleRepository;
import com.github.yangricardo.api_spring_boot.modules.users.repository.IUserRepository;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IRoleRepository roleRepository;

  public UserService(
    IUserRepository userRepository,
    IRoleRepository roleRepository
  ) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public IUserRepository getUserRepository() {
    return this.getUserRepository();
  }

  @Override
  public IRoleRepository getRoleRepository() {
    return this.getRoleRepository();
  }

  @Override
  public Optional<User> findById(Long id) {
    return this.userRepository.findById(id);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  @Override
  public User create(User user) {
    Role userRole = this.createRoleIfNotFound("ROLE_USER");
    Set<Role> userRoleSet = Set.of(userRole);
    user.setRoles(userRoleSet);
    return this.userRepository.save(user);
  }

  @Override
  @Transactional
  public Role createRoleIfNotFound(String roleName) {
    Optional<Role> roleFound = roleRepository.findByRole(roleName);
    if(!roleFound.isPresent()) {
      Role role = Role.builder().role(roleName).build();
      return roleRepository.save(role);
    }
    return roleFound.get();
  }


}

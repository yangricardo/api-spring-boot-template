package br.pucrio.les.esg_token_backend.resources.users.services;

import java.util.Optional;

import br.pucrio.les.esg_token_backend.resources.users.model.User;

public interface IUserService {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User create(User user);

}

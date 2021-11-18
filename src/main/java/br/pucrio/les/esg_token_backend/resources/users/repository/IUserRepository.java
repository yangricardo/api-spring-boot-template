package br.pucrio.les.esg_token_backend.resources.users.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.pucrio.les.esg_token_backend.resources.users.model.User;

@Repository
@Transactional
public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

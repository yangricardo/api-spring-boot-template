package br.pucrio.les.esg_token_backend.resources.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.pucrio.les.esg_token_backend.resources.users.model.User;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {

}

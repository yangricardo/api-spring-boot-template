package br.pucrio.les.esg_token_backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucrio.les.esg_token_backend.models.Value;

@Repository
@Transactional
public interface IValueRepository extends JpaRepository<Value, Long> {

}

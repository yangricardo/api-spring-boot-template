package br.pucrio.les.esg_token_backend.resources.value.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucrio.les.esg_token_backend.resources.value.model.Value;

@Repository
@Transactional
public interface IValueRepository extends JpaRepository<Value, Long> {

}

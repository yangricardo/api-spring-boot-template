package br.pucrio.les.esg_token_backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucrio.les.esg_token_backend.models.EsgTransfer;

@Repository
@Transactional
public interface IEsgTransferRepository extends JpaRepository<EsgTransfer, Long> {

}

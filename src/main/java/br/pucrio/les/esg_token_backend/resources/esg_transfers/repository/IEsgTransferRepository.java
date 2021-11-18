package br.pucrio.les.esg_token_backend.resources.esg_transfers.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucrio.les.esg_token_backend.resources.esg_transfers.model.EsgTransfer;

@Repository
@Transactional
public interface IEsgTransferRepository extends JpaRepository<EsgTransfer, Long> {

}

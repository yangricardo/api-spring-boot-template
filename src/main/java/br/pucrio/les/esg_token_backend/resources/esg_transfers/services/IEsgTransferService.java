package br.pucrio.les.esg_token_backend.resources.esg_transfers.services;

import java.util.List;
import java.util.Optional;

import br.pucrio.les.esg_token_backend.resources.esg_transfers.model.EsgTransfer;

public interface IEsgTransferService {

    public List<EsgTransfer> index();

    public EsgTransfer create(EsgTransfer esgTransfer);

    public Optional<EsgTransfer> findById(Long id);

    public Optional<EsgTransfer> update(Long id, EsgTransfer esgTransfer);

    public void delete(Long id);
}

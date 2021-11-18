package br.pucrio.les.esg_token_backend.services.esg_transfer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucrio.les.esg_token_backend.models.EsgTransfer;
import br.pucrio.les.esg_token_backend.repository.IEsgTransferRepository;

@Service
public class EsgTransferServiceImpl implements IEsgTransferService {

    @Autowired
    private IEsgTransferRepository esgTransferRepository;

    public EsgTransferServiceImpl(IEsgTransferRepository esgTransferRepository) {
        this.esgTransferRepository = esgTransferRepository;
    }

    @Override
    public List<EsgTransfer> index() {
        return this.esgTransferRepository.findAll();
    }

    @Override
    public EsgTransfer create(EsgTransfer esgTransfer) {
        return this.esgTransferRepository.save(esgTransfer);
    }

    @Override
    public Optional<EsgTransfer> findById(Long id) {
        return this.esgTransferRepository.findById(id);
    }

    @Override
    public Optional<EsgTransfer> update(Long id, EsgTransfer esgTransfer) {
        return this.findById(id).map(record -> {
            return this.esgTransferRepository.save(esgTransfer);
        });
    }

    @Override
    public void delete(Long id) {
        this.esgTransferRepository.deleteById(id);
    }
}

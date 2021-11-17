package br.pucrio.les.esg_token_backend.services.value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucrio.les.esg_token_backend.repository.IValueRepository;

@Service
public class ValueServiceImpl implements IValueService {

    @Autowired
    private IValueRepository valueRepository;

    public ValueServiceImpl(IValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }
}

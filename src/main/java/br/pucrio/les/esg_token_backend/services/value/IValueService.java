package br.pucrio.les.esg_token_backend.services.value;

import java.util.List;
import java.util.Optional;

import br.pucrio.les.esg_token_backend.models.Value;

public interface IValueService {

    public List<Value> index();

    public Value create(Value value);

    public Optional<Value> findById(Long id);

    public Optional<Value> update(Long id, Value value);

    public void delete(Long id);
}

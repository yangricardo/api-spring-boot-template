package tech.yang.api_spring_boot.resources.value.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.yang.api_spring_boot.resources.value.model.Value;
import tech.yang.api_spring_boot.resources.value.repository.IValueRepository;

@Service
public class ValueServiceImpl implements IValueService {

    @Autowired
    private IValueRepository valueRepository;

    public ValueServiceImpl(IValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    @Override
    public List<Value> index() {
        return this.valueRepository.findAll();
    }

    @Override
    public Value create(Value value) {
        return this.valueRepository.save(value);
    }

    @Override
    public Optional<Value> findById(Long id) {
        return this.valueRepository.findById(id);
    }

    @Override
    public Optional<Value> update(Long id, Value value) {
        return this.findById(id).map(record -> {
            record.setValue(value.getValue());
            return this.valueRepository.save(record);
        });
    }

    @Override
    public void delete(Long id) {
        this.valueRepository.deleteById(id);
    }
}

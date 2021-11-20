package com.github.yangricardo.api_spring_boot.modules.value.services;

import com.github.yangricardo.api_spring_boot.modules.value.model.Value;
import java.util.List;
import java.util.Optional;

public interface IValueService {
  public List<Value> index();

  public Value create(Value value);

  public Optional<Value> findById(Long id);

  public Optional<Value> update(Long id, Value value);

  public void delete(Long id);
}

package com.github.yangricardo.api_spring_boot.modules.value.repository;

import com.github.yangricardo.api_spring_boot.modules.value.model.Value;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IValueRepository extends JpaRepository<Value, Long> {}

package tech.yang.api_spring_boot.resources.value.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.yang.api_spring_boot.resources.value.model.Value;

@Repository
@Transactional
public interface IValueRepository extends JpaRepository<Value, Long> {

}

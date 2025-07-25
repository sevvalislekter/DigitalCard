package com.cardvisit.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardvisit.entity.EmployeeEntity;
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findByRandomCode(String randomCode);
    List<EmployeeEntity> findByQrActiveTrue();
    List<EmployeeEntity> findByQrActiveFalse();
}


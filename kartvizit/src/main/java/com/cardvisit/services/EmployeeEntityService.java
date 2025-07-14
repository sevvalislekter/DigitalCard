package com.cardvisit.services;

import com.cardvisit.entity.EmployeeEntity;

public interface EmployeeEntityService {
    EmployeeEntity save(EmployeeEntity employeeEntity);
    EmployeeEntity findByRandomCode(String code);
}

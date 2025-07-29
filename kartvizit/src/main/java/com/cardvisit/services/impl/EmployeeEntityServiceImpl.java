package com.cardvisit.services.impl;
import org.springframework.stereotype.Service;
import com.cardvisit.repository.EmployeeRepository;
import com.cardvisit.entity.EmployeeEntity;
import com.cardvisit.services.EmployeeEntityService;

@Service
public class EmployeeEntityServiceImpl implements EmployeeEntityService {
    private final EmployeeRepository employeeRepository;
    public EmployeeEntityServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }
    @Override
    public EmployeeEntity findByRandomCode(String code) {
        return employeeRepository.findByRandomCode(code);
    }
}

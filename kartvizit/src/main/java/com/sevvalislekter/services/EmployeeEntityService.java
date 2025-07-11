package com.sevvalislekter.services;
import org.springframework.stereotype.Service;
import com.sevvalislekter.entity.EmployeeEntity;
import com.sevvalislekter.repository.EmployeeRepository;
@Service
public class EmployeeEntityService {

    private final EmployeeRepository employeeRepository;

    public EmployeeEntityService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }
    public EmployeeEntity findByRandomCode(String code) {
        return employeeRepository.findByRandomCode(code);
    }
}

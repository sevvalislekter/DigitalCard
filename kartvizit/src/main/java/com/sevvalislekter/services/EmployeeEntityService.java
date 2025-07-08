package com.sevvalislekter.services;

import org.springframework.stereotype.Service;
import com.sevvalislekter.entities.Employee;
import com.sevvalislekter.repositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeEntityService {

    private final EmployeeRepository employeeRepository;

    public EmployeeEntityService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findByRandomCode(String code) {
        return employeeRepository.findByRandomCode(code);
    }

   
}

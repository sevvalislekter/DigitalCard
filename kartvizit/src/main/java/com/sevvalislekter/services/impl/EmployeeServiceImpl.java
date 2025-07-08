package com.sevvalislekter.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevvalislekter.dto.DtoEmployee;
import com.sevvalislekter.dto.DtoEmployeeIU;
import com.sevvalislekter.entities.Employee;
import com.sevvalislekter.repositories.EmployeeRepository;
import com.sevvalislekter.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DtoEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIu) {
        
        DtoEmployee response = new DtoEmployee();
        Employee employee = new Employee();
        BeanUtils.copyProperties(dtoEmployeeIu, employee);

        String randomCode = employee.getRandomCode();

        if (randomCode == null || randomCode.isEmpty()) {
            randomCode = java.util.UUID.randomUUID().toString().toUpperCase();
        }
        
        randomCode = randomCode.substring(0, 8);

        

        employee.setRandomCode(randomCode);

        Employee dbEmployee = employeeRepository.save(employee);

        BeanUtils.copyProperties(dbEmployee, response);

        return response;
    }
}

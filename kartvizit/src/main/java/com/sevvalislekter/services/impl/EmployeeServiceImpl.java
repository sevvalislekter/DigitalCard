package com.sevvalislekter.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevvalislekter.dto.DTOEmployee;
import com.sevvalislekter.dto.DtoEmployeeIU;
import com.sevvalislekter.entities.Employee;
import com.sevvalislekter.repository.EmployeeRepository;
import com.sevvalislekter.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

   
    @Override
    public DTOEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIu) {
        
        DTOEmployee response = new DTOEmployee();
        Employee employee = new Employee();
        BeanUtils.copyProperties(dtoEmployeeIu, employee);

        
        if (employee.getRandomCode() == null || employee.getRandomCode().isEmpty()) {
            employee.setRandomCode(java.util.UUID.randomUUID().toString());
        }

        
        Employee dbEmployee = employeeRepository.save(employee);

        
        BeanUtils.copyProperties(dbEmployee, response);

        return response;
    }

        
    
}

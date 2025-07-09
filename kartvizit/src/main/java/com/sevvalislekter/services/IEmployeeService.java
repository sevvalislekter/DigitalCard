package com.sevvalislekter.services;

import java.util.List;

import com.sevvalislekter.dto.EmployeeDTO;
import com.sevvalislekter.dto.EmployeeDTOIU;
import com.sevvalislekter.entity.Employee;

public interface IEmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTOIU employeeDtoIU);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    Employee getEmployeeByRandomCode(String randomCode);

    void deleteEmployee(Integer id); 

}


package com.sevvalislekter.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sevvalislekter.dto.EmployeeDTO;
import com.sevvalislekter.dto.EmployeeDTOIU;

import com.sevvalislekter.services.IEmployeeService;

@RestController
@RequestMapping("/rest/api/employee")
public class EmployeeControllerImpl {

	@Autowired
    private final IEmployeeService employeeService;

    public EmployeeControllerImpl(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/save")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTOIU employeeDTOIU) {
        return employeeService.saveEmployee(employeeDTOIU);
    }
}

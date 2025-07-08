package com.sevvalislekter.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevvalislekter.controller.IEmployeeController;
import com.sevvalislekter.dto.DtoEmployee;
import com.sevvalislekter.dto.DtoEmployeeIU;

import com.sevvalislekter.services.IEmployeeService;

@RestController
@RequestMapping("/rest/api/employee")
public class EmployeeControllerImpl implements IEmployeeController {

	@Autowired
    private final IEmployeeService employeeService;

    public EmployeeControllerImpl(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/save")
    @Override
    public DtoEmployee saveEmployee(@RequestBody DtoEmployeeIU dtoEmployeeIU) {
        return employeeService.saveEmployee(dtoEmployeeIU);
    }
}

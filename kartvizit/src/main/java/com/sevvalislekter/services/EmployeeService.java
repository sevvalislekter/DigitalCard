package com.sevvalislekter.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sevvalislekter.dto.EmployeeDTO;
import com.sevvalislekter.dto.EmployeeIUDTO;
import com.sevvalislekter.entity.EmployeeEntity;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeIUDTO employeeDtoIU);

    List<EmployeeEntity> getAllEmployees(); 
    List<EmployeeEntity> findByQrActiveTrue();
    List<EmployeeEntity> findByQrActiveFalse();
    ResponseEntity<byte[]> generateVCard(String randomCode);
    void deactivateEmployeeByRandomCode(String randomCode);

    void createEmployeeWithPhoto(EmployeeIUDTO dto);
}

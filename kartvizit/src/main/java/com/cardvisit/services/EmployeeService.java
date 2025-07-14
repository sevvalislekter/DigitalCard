package com.cardvisit.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cardvisit.dto.EmployeeDTO;
import com.cardvisit.dto.EmployeeIUDTO;
import com.cardvisit.entity.EmployeeEntity;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeIUDTO employeeDtoIU);

    List<EmployeeEntity> getAllEmployees(); 
    List<EmployeeEntity> findByQrActiveTrue();
    List<EmployeeEntity> findByQrActiveFalse();
    ResponseEntity<byte[]> generateVCard(String randomCode);
    void deactivateEmployeeByRandomCode(String randomCode);
    void createEmployeeWithPhoto(EmployeeIUDTO dto);
   

	
}

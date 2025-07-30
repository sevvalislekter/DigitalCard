package com.cardvisit.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    EmployeeEntity findByRandomCode(String randomCode);
    void updateEmployeeForm(EmployeeIUDTO dto);
    void showUpdate(Model model,String randomCode);
    void QrCard(String randomCode);
    void NfcCard(String randomCode);
    void Cardp(String randomCode);
}

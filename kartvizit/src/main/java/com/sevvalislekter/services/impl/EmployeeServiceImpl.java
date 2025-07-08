package com.sevvalislekter.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.sevvalislekter.Utils.QRCodeGenerator;
import com.sevvalislekter.dto.EmployeeDTO;
import com.sevvalislekter.dto.EmployeeDTOIU;
import com.sevvalislekter.entities.Employee;
import com.sevvalislekter.repositories.EmployeeRepository;
import com.sevvalislekter.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTOIU employeeDtoIU) {
        EmployeeDTO response = new EmployeeDTO();
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDtoIU, employee);

        String randomCode = employee.getRandomCode();

        if (randomCode == null || randomCode.isEmpty()) {
            randomCode = java.util.UUID.randomUUID().toString().toUpperCase();
        }

        if (randomCode.length() > 8) {
            randomCode = randomCode.substring(0, 8);
        }

        employee.setRandomCode(randomCode);

        Employee savedEmployee = employeeRepository.save(employee);

        String staticPath = new File("src/main/resources/static/uploads/qr-codes").getAbsolutePath();
        File uploadDir = new File(staticPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String qrContent = "http://localhost:8080/p/" + savedEmployee.getRandomCode(); // Kullanıcı profil linki

        // ✅ DOĞRU DOSYA YOLU: static klasörüne tam olarak yazıyoruz
        String qrFilePath = staticPath + File.separator + savedEmployee.getRandomCode() + ".png";

        try {
            QRCodeGenerator.generateQRCodeImage(qrContent, qrFilePath, 250, 250);
            System.out.println("QR başarıyla yazıldı: " + qrFilePath);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }


        savedEmployee.setQrCodeUrl("/uploads/qr-codes/" + savedEmployee.getRandomCode() + ".png");
        employeeRepository.save(savedEmployee);



        BeanUtils.copyProperties(savedEmployee, response);
        return response;
    }

    

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee getEmployeeByRandomCode(String randomCode) {
        return employeeRepository.findByRandomCode(randomCode);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}

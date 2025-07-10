package com.sevvalislekter.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.google.zxing.WriterException;
import com.sevvalislekter.Utils.QRCodeGenerator;
import com.sevvalislekter.dto.EmployeeDTO;
import com.sevvalislekter.dto.EmployeeIUDTO;
import com.sevvalislekter.entity.EmployeeEntity;
import com.sevvalislekter.repository.EmployeeRepository;
import com.sevvalislekter.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    

    private final EmployeeRepository employeeRepository;
    

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deactivateEmployeeByRandomCode(String randomCode) {
        EmployeeEntity employee = employeeRepository.findByRandomCode(randomCode);
        if (employee != null) {
            employee.setExitDate(LocalDate.now());
            employee.setQrActive(false);
            employeeRepository.save(employee); 
        }
    }

    
    public void createEmployeeWithPhoto(EmployeeIUDTO dto) {

        
        if (dto.getQrActive() == null) {
            dto.setQrActive(true);
        }

        String photoUrl = null;
        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            try {
                String fileName = dto.getFirstName() + dto.getLastName() + ".jpg";
                Path uploadDir = Paths.get("src/main/resources/static/uploads/profile-photos");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(fileName);
                Files.copy(dto.getPhoto().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                photoUrl = "/uploads/profile-photos/" + fileName;
                dto.setPhotoUrl(photoUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

     
        EmployeeDTO savedEmployee = this.saveEmployee(dto);


     
        EmployeeEntity employeeForLdap = new EmployeeEntity();
        BeanUtils.copyProperties(savedEmployee, employeeForLdap);
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeIUDTO employeeDtoIU) {
        EmployeeDTO response = new EmployeeDTO();
        EmployeeEntity employeeEntity = new EmployeeEntity();

        BeanUtils.copyProperties(employeeDtoIU, employeeEntity);

        String randomCode = employeeEntity.getRandomCode();

        if (randomCode == null || randomCode.isEmpty()) {
            randomCode = java.util.UUID.randomUUID().toString().toUpperCase();
        }

        if (randomCode.length() > 8) {
            randomCode = randomCode.substring(0, 8);
        }

        employeeEntity.setRandomCode(randomCode);

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        String staticPath = new File("src/main/resources/static/uploads/qr-codes").getAbsolutePath();
        File uploadDir = new File(staticPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String qrContent = "http://localhost:8080/p/" + savedEmployee.getRandomCode(); 

    
        String qrFilePath = staticPath + File.separator + savedEmployee.getFirstName()+savedEmployee.getLastName() + ".png";

        try {
            QRCodeGenerator.generateQRCodeImage(qrContent, qrFilePath, 250, 250);
            System.out.println("QR başarıyla yazıldı: " + qrFilePath);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }


        savedEmployee.setQrCodeUrl("/uploads/qr-codes/" + savedEmployee.getFirstName()+savedEmployee.getLastName() + ".png");
        employeeRepository.save(savedEmployee);



        BeanUtils.copyProperties(savedEmployee, response);
        return response;
    }
    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findByQrActiveTrue(); 
    }

    @Override
    public List<EmployeeEntity> findByQrActiveTrue() {
        return employeeRepository.findByQrActiveTrue();
    }

    @Override
    public List<EmployeeEntity> findByQrActiveFalse() {
        return employeeRepository.findByQrActiveFalse(); 
    }
    
    @Override
    public ResponseEntity<byte[]> generateVCard(String randomCode) {
        EmployeeEntity employeeEntity = employeeRepository.findByRandomCode(randomCode.trim());

        if (employeeEntity == null) {
            return ResponseEntity.notFound().build();
        }

        StringBuilder vcard = new StringBuilder();
        vcard.append("BEGIN:VCARD\r\n");
        vcard.append("VERSION:3.0\r\n");
        vcard.append("FN:").append(employeeEntity.getFirstName()).append(" ").append(employeeEntity.getLastName()).append("\r\n");
        vcard.append("EMAIL:").append(employeeEntity.getEmail()).append("\r\n");
        vcard.append("TEL:").append(employeeEntity.getPhoneNumber()).append("\r\n");
        vcard.append("TITLE:").append(employeeEntity.getTitle()).append("\r\n");
        vcard.append("URL:").append(employeeEntity.getLinkedinUrl()).append("\r\n");
        vcard.append("END:VCARD\r\n");

        byte[] vcfBytes = vcard.toString().getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/vcard"));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(employeeEntity.getFirstName() + employeeEntity.getLastName() + ".vcf")
                .build());

        return ResponseEntity.ok().headers(headers).body(vcfBytes);
    }

    
    
}

   

    
    


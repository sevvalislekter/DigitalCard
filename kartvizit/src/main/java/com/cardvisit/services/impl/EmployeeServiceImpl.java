package com.cardvisit.services.impl;

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
import org.springframework.ui.Model;

import com.cardvisit.Utils.QRCodeGenerator;
import com.cardvisit.dto.EmployeeDTO;
import com.cardvisit.dto.EmployeeIUDTO;
import com.cardvisit.entity.EmployeeEntity;
import com.cardvisit.repository.EmployeeRepository;
import com.cardvisit.services.EmployeeService;
import com.google.zxing.WriterException;

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

    @Override
    public void createEmployeeWithPhoto(EmployeeIUDTO dto) {
        dto.setQrActive(true);

        if (dto.getRandomCode() == null || dto.getRandomCode().isEmpty()) {
            String randomCode = java.util.UUID.randomUUID().toString().toUpperCase().substring(0, 8);
            dto.setRandomCode(randomCode);
        }

        
        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            try {
                String fileName = dto.getRandomCode() + ".jpg";
                Path uploadDir = Paths.get("uploads/profile-photos");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(fileName);
                Files.copy(dto.getPhoto().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                String photoUrl = "/uploads/profile-photos/" + fileName;
                dto.setPhotoUrl(photoUrl);

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Fotoğraf yüklenirken hata oluştu: " + e.getMessage(), e);
            }
        }

        this.saveEmployee(dto); 
    }

    @Override
    public void updateEmployeeForm(EmployeeIUDTO dto) {
        EmployeeEntity entity = employeeRepository.findByRandomCode(dto.getRandomCode());
        if (entity == null) return;

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setTitle(dto.getTitle());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setLinkedinUrl(dto.getLinkedinUrl());

      
        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            try {
                String fileName = dto.getRandomCode() + ".jpg";
                Path uploadDir = Paths.get("uploads/profile-photos");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(fileName);
                Files.copy(dto.getPhoto().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                String photoUrl = "/uploads/profile-photos/" + fileName;
                entity.setPhotoUrl(photoUrl); 
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Fotoğraf güncellenirken hata oluştu: " + e.getMessage(), e);
            }
        } else if (dto.getPhotoUrl() != null) {
            
            entity.setPhotoUrl(dto.getPhotoUrl());
        }

        if (dto.getQrCodeUrl() != null) {
            entity.setQrCodeUrl(dto.getQrCodeUrl());
        }

        employeeRepository.save(entity);
    }


    @Override
    public void showUpdate(Model model, String randomCode) {
        EmployeeEntity employeeEntity = employeeRepository.findByRandomCode(randomCode);
        if (employeeEntity == null) {
            model.addAttribute("notfound", true);
            return;
        }

        EmployeeIUDTO employeeIUDTO = new EmployeeIUDTO();
        BeanUtils.copyProperties(employeeEntity, employeeIUDTO);
        employeeIUDTO.setPhotoUrl(employeeEntity.getPhotoUrl());
        employeeIUDTO.setQrCodeUrl(employeeEntity.getQrCodeUrl());
        model.addAttribute("employee", employeeIUDTO);
    }

    @Override
    public EmployeeEntity findByRandomCode(String randomCode) {
        return employeeRepository.findByRandomCode(randomCode);
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeIUDTO dto) {
        EmployeeDTO response = new EmployeeDTO();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(dto, employeeEntity);

        String randomCode = employeeEntity.getRandomCode();
        if (randomCode == null || randomCode.isEmpty()) {
            randomCode = java.util.UUID.randomUUID().toString().toUpperCase();
        }
        if (randomCode.length() > 8) {
            randomCode = randomCode.substring(0, 8);
        }
        employeeEntity.setRandomCode(randomCode);

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

       
        String staticPath = new File("uploads/qr-codes").getAbsolutePath();
        File uploadDir = new File(staticPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String qrContent = "http://localhost:8080/p/" + savedEmployee.getRandomCode();
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

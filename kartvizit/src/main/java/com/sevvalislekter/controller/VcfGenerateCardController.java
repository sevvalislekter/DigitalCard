package com.sevvalislekter.controller;

import com.sevvalislekter.entities.Employee;
import com.sevvalislekter.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")  
public class VcfGenerateCardController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/vcard/{randomCode}")
    public ResponseEntity<byte[]> generateVCard(@PathVariable String randomCode) {
        Employee employee = employeeRepository.findByRandomCode(randomCode.trim());

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        StringBuilder vcard = new StringBuilder();
        vcard.append("BEGIN:VCARD\r\n");
        vcard.append("VERSION:3.0\r\n");
        vcard.append("FN:").append(employee.getFirstName()).append(" ").append(employee.getLastName()).append("\r\n");
        vcard.append("EMAIL:").append(employee.getEmail()).append("\r\n");
        vcard.append("TEL:").append(employee.getPhoneNumber()).append("\r\n");
        vcard.append("TITLE:").append(employee.getTitle()).append("\r\n");
        vcard.append("URL:").append(employee.getLinkedinUrl()).append("\r\n");
        vcard.append("END:VCARD\r\n");

        byte[] vcfBytes = vcard.toString().getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/vcf"));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(employee.getFirstName() + employee.getLastName() + ".vcf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(vcfBytes);
    }

}

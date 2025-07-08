package com.sevvalislekter.controller;

import com.sevvalislekter.entities.Employee;
import com.sevvalislekter.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
public class VcfGenerateCard {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/vcard/{code}")
    public ResponseEntity<byte[]> generateVCard(@PathVariable String code) {
        Employee employee = employeeRepository.findByRandomCodeCaseInsensitive(code);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        StringBuilder vcard = new StringBuilder();
        vcard.append("BEGIN:VCARD\n");
        vcard.append("VERSION:3.0\n");
        vcard.append("FN:").append(employee.getFirstName()).append("\n");
        vcard.append("FN:").append(employee.getLastName()).append("\n");
        vcard.append("EMAIL:").append(employee.getEmail()).append("\n");
        vcard.append("TEL:").append(employee.getPhoneNumber()).append("\n");
        vcard.append("TITLE:").append(employee.getTitle()).append("\n");
        vcard.append("URL:").append(employee.getLinkedinUrl()).append("\n");
        vcard.append("END:VCARD");

        byte[] vcfBytes = vcard.toString().getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/vcard"));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(employee.getFirstName()+employee.getLastName()+".vcf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(vcfBytes);
    }
}

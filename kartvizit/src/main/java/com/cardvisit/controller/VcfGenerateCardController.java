package com.cardvisit.controller;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.cardvisit.services.EmployeeService;
@RestController
@RequestMapping("/api")
public class VcfGenerateCardController {

    private final EmployeeService employeeService;
    public VcfGenerateCardController(EmployeeService employeeService) {

        this.employeeService = employeeService;

    }
    @GetMapping("/vcard/{randomCode}")
    public ResponseEntity<byte[]> generateVCard(@PathVariable String randomCode) {
        return employeeService.generateVCard(randomCode); 
    }
}


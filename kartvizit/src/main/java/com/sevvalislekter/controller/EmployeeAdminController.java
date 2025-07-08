package com.sevvalislekter.controller;

import com.sevvalislekter.dto.EmployeeDTO;
import com.sevvalislekter.dto.EmployeeDTOIU;
import com.sevvalislekter.entities.Employee;
import com.sevvalislekter.services.IEmployeeService;
import com.sevvalislekter.services.LdapEmployeeService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeAdminController {

    private final IEmployeeService iEmployeeService;
    private final LdapEmployeeService ldapEmployeeService;

    public EmployeeAdminController(IEmployeeService iEmployeeService, LdapEmployeeService ldapEmployeeService) {
        this.iEmployeeService = iEmployeeService;
        this.ldapEmployeeService = ldapEmployeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        var employees = iEmployeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "admin/employees";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new EmployeeDTOIU());
        return "admin/employee-form";
    }

    @PostMapping("/create")
    public String createEmployee(
            @ModelAttribute EmployeeDTOIU dto,
            @RequestParam(required = false) MultipartFile photo
    ) {
        // 1. Fotoğrafı kaydet
        String photoUrl = null;
        if (photo != null && !photo.isEmpty()) {
            try {
                String fileName = java.util.UUID.randomUUID().toString() + ".jpg";
                Path uploadDir = Paths.get("src/main/resources/static/uploads/profile-photos");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(fileName);
                Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                photoUrl = "/uploads/profile-photos/" + fileName;
                dto.setPhotoUrl(photoUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 2. Veritabanına kaydet
        EmployeeDTO savedEmployee = iEmployeeService.saveEmployee(dto);

        // 3. LDAP’a da kaydet
        Employee employeeForLdap = new Employee();
        BeanUtils.copyProperties(savedEmployee, employeeForLdap);
        ldapEmployeeService.addEmployee(employeeForLdap, photo);

        return "redirect:/admin/employees";
    }
}

package com.sevvalislekter.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sevvalislekter.entity.Employee;
import com.sevvalislekter.repository.EmployeeRepository;

@Controller
public class ProfileController {

    private final EmployeeRepository employeeRepository;

    public ProfileController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/p/{randomCode}")
    public String showProfile(@PathVariable String randomCode, Model model) {
        System.out.println("Aranan randomCode: " + randomCode);

        Employee employee = employeeRepository.findByRandomCode(randomCode.trim());

        if (employee == null) {
            return "redirect:/notFound";
        }

        System.out.println("Bulunan employee: " + employee.getId());

        model.addAttribute("employee", employee);
        return "profile";
    }



    @GetMapping("/notFound")
    public String notFoundPage() {
        return "notfound";
    }
}
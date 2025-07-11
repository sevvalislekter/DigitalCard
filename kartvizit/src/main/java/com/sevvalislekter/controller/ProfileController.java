package com.sevvalislekter.controller;

import com.sevvalislekter.services.EmployeeServiceProfile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sevvalislekter.dto.EmployeeDTO;

@Controller
public class ProfileController {

    private final EmployeeServiceProfile employeeServiceProfile;

    public ProfileController(EmployeeServiceProfile employeeServiceProfile) {
        this.employeeServiceProfile = employeeServiceProfile;
    }

    @GetMapping("/p/{randomCode}")
    public String showProfile(@PathVariable String randomCode, Model viewData) {
        EmployeeDTO dto = employeeServiceProfile.ShowProfileForUser(randomCode);
        if (dto == null) {
            return "redirect:/notFound";
        }
        viewData.addAttribute("employee", dto);
        return "profile";
    }

    @GetMapping("/notFound")
    public String notFoundPage() {
        return "notfound";
    }
}

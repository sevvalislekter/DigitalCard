package com.cardvisit.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cardvisit.dto.EmployeeDTO;
import com.cardvisit.services.EmployeeServiceProfile;

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
        }if(dto.getQrActive()==false) {
        	return "redirect:/qr-disabled";
        }
        viewData.addAttribute("employee", dto);
        return "profile";
    }
    @GetMapping("/notFound")
    public String notFoundPage() {
        return "notfound";
    }
    @GetMapping("/qr-disabled")
    public String qrDisabledPage() {
        return "error/qr-disabled"; 
    }
}
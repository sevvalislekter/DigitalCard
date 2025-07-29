package com.cardvisit.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cardvisit.dto.EmployeeIUDTO;
import com.cardvisit.services.EmployeeService;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeAdminController {
    private final EmployeeService employeeService;
    
    public EmployeeAdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
       
    }
    @GetMapping
    public String listEmployees(Model viewData) {
        var employees = employeeService.getAllEmployees();
        viewData.addAttribute("employees", employees);
        return "admin/employees";
    }
    @GetMapping("/create")
    public String showCreateForm(Model viewData) {
    	viewData.addAttribute("employee", new EmployeeIUDTO());
        return "admin/employee-form";
    }
    @GetMapping("/ex/{randomCode}")
    public String deactiveQr(@PathVariable String randomCode) {
      employeeService.deactivateEmployeeByRandomCode(randomCode);
        return "redirect:/admin/employees/exEmployees";
    }
    @GetMapping("/exEmployees")
    public String showExEmployees(Model viewData) {
        var exEmployees = employeeService.findByQrActiveFalse();
        viewData.addAttribute("employees", exEmployees);
        return "admin/exEmployees"; 
    }
    @PostMapping("/create")
    public String createEmployee(@ModelAttribute EmployeeIUDTO dto) {
        employeeService.createEmployeeWithPhoto(dto);
        return "redirect:/admin/employees";
    }
    @GetMapping("/update/{randomCode}")
    public String showUpdateEmployee(Model model, @PathVariable String randomCode) {
        employeeService.showUpdate(model, randomCode);
        return "admin/updateEmployee";
    }
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute EmployeeIUDTO dto) {
        employeeService.updateEmployeeForm(dto);
        return "redirect:/admin/employees"; 
    }
}
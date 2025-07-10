package com.sevvalislekter.controller;
import com.sevvalislekter.dto.EmployeeIUDTO;
import com.sevvalislekter.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "admin/exEmployees"; // 
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute EmployeeIUDTO dto) {
        employeeService.createEmployeeWithPhoto(dto);
        return "redirect:/admin/employees";
    }

}

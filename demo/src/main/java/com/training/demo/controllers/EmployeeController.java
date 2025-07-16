package com.training.demo.controllers;

import com.training.demo.models.RegisterDetails;
import com.training.demo.models.UserDetailsDto;
import com.training.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String route(){
        return "Welcome to Springboot Security";
    }
    @GetMapping("/get")
    public List<RegisterDetails> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<RegisterDetails> getMethod(){
        return employeeService.getMethod();
    }

    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public RegisterDetails getEmployeeById(@PathVariable int empId){
        System.out.println();
        return employeeService.getEmployeeById(empId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    public String postMethod(@RequestBody UserDetailsDto employee){
        return employeeService.addEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employee/{empId}")
    public String putMethod(@PathVariable int empId,@RequestBody UserDetailsDto details){
        return employeeService.updateEmployee(empId,details);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employee/{empID}")
    public String deleteMethod(@PathVariable int empID){
        return employeeService.deleteEmployee(empID);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee/role/{role}")
    public RegisterDetails getEmployeeByRole(@PathVariable String role){
        return employeeService.getEmployeeByRole(role);
    }

}
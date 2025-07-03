package com.training.demo.services;

import com.training.demo.models.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    public List<Employee> getAllEmployees() {
        return Arrays.asList(
                new Employee(1, "Prasanth", "Trainer"),
                new Employee(2, "Yuvaraj", "Faculty"),
                new Employee(3, "Arun", "Developer")
        );
    }
}

package com.training.demo.services;
import com.training.demo.models.RegisterDetails;
import com.training.demo.models.Roles;
import com.training.demo.models.UserDetailsDto;
import com.training.demo.repository.RegisterDetailsRepository;
import com.training.demo.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    RegisterDetailsRepository registerDetailsRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public RolesRepository rolesRepository;

    public List<RegisterDetails> getAllEmployees(){
        return registerDetailsRepository.findAll();
    }

    public List<RegisterDetails> getMethod() {
        return registerDetailsRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empId) {
        return registerDetailsRepository.findById(empId).orElse(new RegisterDetails());
    }

//    public List<RegisterDetails> getEmployeeByJob() {
//        return registerDetailsRepository.findByRole();
//    }
public String addEmployee(UserDetailsDto register) {
    RegisterDetails registerDetails = new RegisterDetails();
    registerDetails.setEmpId(register.getEmpId());
    registerDetails.setName(register.getName());
    registerDetails.setEmail(register.getEmail());
    registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
    registerDetails.setUserName(register.getUserName());
    Set<Roles> roles = new HashSet<>();
    for(String roleName: register.getRoleNames()){
        Roles role = rolesRepository.findByRoleName(roleName)
                .orElseThrow(()->new RuntimeException("User not found" + roleName));
        roles.add(role);
    }
    registerDetails.setRoles(roles);
    System.out.println("Registration"+ registerDetails);
    registerDetailsRepository.save(registerDetails);
    return "Employee Added Successfully";
}

    @PutMapping("/update")
    public String updateEmployee(int empId, UserDetailsDto details) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(()->new RuntimeException("No Such User Present"));
        user.setName(details.getName());
        user.setEmail(details.getEmail());
        user.setUserName(details.getUserName());
        user.setPassword(details.getPassword());
        registerDetailsRepository.save(user);
        return "Employee Updated Successfully";
    }
    public String deleteEmployee(int empID) {
        registerDetailsRepository.deleteById(empID);
        return "Employee Deleted Successfully";
    }

    public RegisterDetails getEmployeeByRole(String role) {
        return registerDetailsRepository.findByRole(role).orElse(new RegisterDetails());
    }


}
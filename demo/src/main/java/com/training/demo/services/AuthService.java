package com.training.demo.services;
import com.training.demo.models.RegisterDetails;
import com.training.demo.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    RegisterDetailsRepository registerDetailsRepository;



    //post
    public String addNewService(RegisterDetails register) {
        RegisterDetails registerDetails=new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setGender(register.getGender());
        registerDetails.setRole(register.getRole());
        registerDetails.setDateOfBirth(register.getDateOfBirth());
        registerDetails.setEmpName(register.getEmpName());
        registerDetails.setPassword(passwordEncoder().encode(register.getPassword()));
        registerDetailsRepository.save(registerDetails);
        return "Employee added successfully";
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<RegisterDetails> getService() {
        return registerDetailsRepository.findAll();
    }


    //Login
    public String authPostLoginService(RegisterDetails login){
        RegisterDetails user=registerDetailsRepository.findByEmail(login.getEmail());
        if(user!=null){
            if(passwordEncoder().matches(login.getPassword(),user.getPassword())){
                return "Login Successfully";
            }
        }
        return "Login Not Successfull";
    }
}
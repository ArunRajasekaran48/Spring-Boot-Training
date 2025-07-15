package com.training.demo.service;

import com.training.demo.models.RegisterDetails;
import com.training.demo.repository.RegisterDetailsRepository;
import com.training.demo.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class EmployeeServiceTest {
    @Mock//not authentic or real,
    RegisterDetailsRepository registerDetailsRepository;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetMethod(){
        RegisterDetails empl1=new RegisterDetails();
        RegisterDetails empl2=new RegisterDetails();

        when(registerDetailsRepository.findAll()).thenReturn(Arrays.asList(empl1,empl2));//when is present inside mokito//this line do (when we call for registerDetailsRepository.findAll() by usinggetAllEmployeeees in service then return the empl1 and empl2 as list
        List<RegisterDetails> result=employeeService.getAllEmployees();//if we findall the employee then return the above empl1,empl2 as list
        System.out.println(result);
        assertEquals(2,result.size());//as result has 2 list empl1 and empl2

    }
}



package com.example.Spring.Security.Basic.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/managment")
@RestController
public class ManagmentAPI {
    @GetMapping
    public String GetAllManagmentStudents(){
        return "Get all Managment Student Managment Controller";
    }

    @PostMapping
    public String AddAStudent(){
        return "Add a Student Managment Controller";
    }
}

package com.example.Spring.Security.Basic.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1/students")
@RestController
public class StudentAPI {
    @GetMapping
    public String getAllStudents(){
        return "I am student Controller and I am returning all Students";
    }

    @PostMapping
    public String addAStudent(){
        return "I am student Controller and I am adding a student";
    }
}

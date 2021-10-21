package com.example.Spring.Security.Basic.Api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/public")
@RestController
public class PublicController {
    @GetMapping
    public String getAllStudent(){
        return "Get all students public controller";
    }

    @PostMapping
    public String AddAStudent(){
        return "Add a students public controller";
    }
}

package com.bilal.employeeapp.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {
   
    
    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }
    
    @GetMapping("/employee-create")
    public String createEmployeeForm() {
        return "employeeCreate";     }
    
    
   
}


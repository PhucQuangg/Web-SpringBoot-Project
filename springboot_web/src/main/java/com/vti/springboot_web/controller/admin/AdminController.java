package com.vti.springboot_web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String index(){
        return "redirect:/admin/dashboard";
    }
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }
}

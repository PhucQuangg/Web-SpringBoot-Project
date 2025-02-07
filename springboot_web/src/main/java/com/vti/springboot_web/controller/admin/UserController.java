package com.vti.springboot_web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {
    @GetMapping("/user")
    public String index(){
        return "admin/user/index";
    }

}

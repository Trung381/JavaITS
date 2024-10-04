package com.example.vuvantrung.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    // Endpoint công khai, không yêu cầu xác thực
    @GetMapping("/haizzz")
    public String hello() {
        return "haizzzzzzz";
    }

    // Endpoint yêu cầu xác thực
    @PostMapping("/customer/list")
    public String getCustomerList() {
        return "Customer List";
    }
    @GetMapping("/login")
    public String login() {
        return "Login page";
    }


}


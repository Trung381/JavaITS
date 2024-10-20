package com.example.vuvantrung.controller;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")

public class MyController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    @PostMapping("/greeting")
    public String postGreeting(@RequestBody String name) {
        return "Hello, " + name;
    }

    @GetMapping("/greet")
    public String greet(@RequestBody String name) {
        return "Hi " + name + ", welcome!";
    }
}

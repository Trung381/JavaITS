package com.example.springproject.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")

public class MyController {
    // Xử lý HTTP GET request tại "/api/hello"
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    // Xử lý HTTP POST request tại "/api/greeting"
    @PostMapping("/greeting")
    public String postGreeting(@RequestBody String name) {
        return "Hello, " + name;
    }

    // Xử lý HTTP GET request với tham số
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hi " + name + ", welcome!";
    }
}

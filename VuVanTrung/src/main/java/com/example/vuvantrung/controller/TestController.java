package com.example.vuvantrung.controller;

import com.example.vuvantrung.Character;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    // Trả về biến đó ( đúng với kiểu biến đó)
    @GetMapping("/request-param")
    @ResponseBody
    public String requestParam(@RequestParam String name) {
        return name + "Hello, world!";
    }

    // Trả về JSON khi kết hợp với @ResponseBody
    @GetMapping("/request-body")
    @ResponseBody
    public String requestBody(@RequestBody Character cha) {
        return "Hello, world!" + cha;
    }
}
package com.example.springproject.Controller;

import com.example.springproject.Character;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    // Trả về biến đó ( đúng với kiểu biến đó)
    @GetMapping("/requestParam")
    @ResponseBody
    public String requestParam(@RequestParam String name) {
        return name + "Hello, world!";
    }

    // Trả về JSON khi kết hợp với @ResponseBody
    @GetMapping("/requestBody")
    @ResponseBody
    public String requestBody(@RequestBody Character cha) {
        return "Hello, world!" + cha;
    }
}

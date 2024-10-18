package com.example.vuvantrung.controller;

import com.example.vuvantrung.service.UserService;
//import com.example.vuvantrung.dto.UserUsageHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


}

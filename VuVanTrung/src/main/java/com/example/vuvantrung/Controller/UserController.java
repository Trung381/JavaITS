package com.example.vuvantrung.Controller;

import com.example.vuvantrung.Entity.User;
import com.example.vuvantrung.Entity.UsageHistory;
import com.example.vuvantrung.Service.UserService;
//import com.example.vuvantrung.dto.UserUsageHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


}

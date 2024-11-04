package com.example.vuvantrung.controller;

import com.example.vuvantrung.entity.User;
import com.example.vuvantrung.service.UserService;
//import com.example.vuvantrung.dto.UserUsageHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/role")
    public ResponseEntity<List<User>> getUsersByRoles(@RequestParam("roles") String role) {
        List<User> users = userService.findAllUsersWithRoles(role);
        return ResponseEntity.ok(users);
    }
}

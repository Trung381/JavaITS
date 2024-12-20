package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.User;
import com.example.vuvantrung.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<User> findAllUsersWithRoles(String role) {
        return userRepository.findAllUserWithRole(role);
    }
}


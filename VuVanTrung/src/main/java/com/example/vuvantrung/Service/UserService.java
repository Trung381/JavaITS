package com.example.vuvantrung.Service;

import com.example.vuvantrung.Entity.User;
import com.example.vuvantrung.Exception.UserNotFoundException;
import com.example.vuvantrung.Repository.UserRepository;
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


}


package com.example.vuvantrung.service;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {
    private int count = 0;

    @Override
    public void performAction() {
        System.out.println("Action performed by MyServiceImpl");
    }

    public void performTask(){
        System.out.println(count++);
    }
}

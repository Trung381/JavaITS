package com.example.vuvantrung.Service;

import com.example.vuvantrung.Entity.Car;
import com.example.vuvantrung.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


}
package com.example.vuvantrung.controller;

import com.example.vuvantrung.entity.Car;
import com.example.vuvantrung.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @PostMapping(path = "/add")
    public @ResponseBody String addNewCar(@RequestBody Car car) {
        carService.addCar(car);
        return "Saved";
    }

    @GetMapping("/get_car/{id}")
    public @ResponseBody Optional<Car> getCarById(@PathVariable int id){
        return carService.getCarById(id);
    }

    @GetMapping(path = "/get_all")
    public @ResponseBody Iterable<Car> getAllCars() {
        return carService.getAllCar();
    }

    @PostMapping(path = "/update/{id}")
    public @ResponseBody String updateCar(@PathVariable Integer id, @RequestBody Car carDetails) {
        carService.updateCar(id, carDetails);
        return "Updated";
    }

    @GetMapping(path = "/delete/{id}")
    public @ResponseBody String deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
        return "Deleted";
    }
}
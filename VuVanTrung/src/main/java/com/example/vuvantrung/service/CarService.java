package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Car;
import com.example.vuvantrung.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCar(){ return (List<Car>) carRepository.findAll();}

    public Optional<Car> getCarById(int id){return carRepository.findById(id);}

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public Car updateCar(int id, Car carDetails){
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        return carRepository.save(car);
    }

    public Car deleteCar(int id){
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        carRepository.delete(car);
        return car;
    }

}
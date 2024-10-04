package com.example.vuvantrung.Controller;

import com.example.vuvantrung.Entity.Car;
import com.example.vuvantrung.Repository.CarRepository;
import com.example.vuvantrung.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This marks the class as a controller
@RequestMapping(path = "/cars") // URL starts with /cars
public class CarController {

    @Autowired // This injects the CarRepository bean automatically
    private CarRepository carRepository;


    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody String addNewCar(@RequestBody Car car) {
        // Lưu đối tượng Car vào cơ sở dữ liệu
        carRepository.save(car);
        return "Saved";
    }


    @GetMapping(path = "/all") // Map GET requests to fetch all cars
    public @ResponseBody Iterable<Car> getAllCars() {
        // Return a list of all cars in the repository
        return carRepository.findAll();
    }

    @PutMapping(path = "/update/{id}") // Map PUT requests to update a car
    public @ResponseBody String updateCar(@PathVariable Integer id, @RequestBody Car carDetails) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        carRepository.save(car);
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{id}") // Map DELETE requests to delete a car
    public @ResponseBody String deleteCar(@PathVariable Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        carRepository.delete(car);
        return "Deleted";
    }
}
package com.rent.rest_api_impl;

import com.rent.model.dto.CarDto;
import com.rent.rest_api.CarController;
import com.rent.service_api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarControllerImpl implements CarController {

    @Autowired
    private CarService carService;

    @Override
    @GetMapping("/car/{id}")
    public CarDto getCar(@RequestParam Long id) {
        return null;
    }

    @Override
    @GetMapping("/car/list")
    public List<CarDto> getAllCars() {
        return null;
    }

    @Override
    @PostMapping("/car/add")
    public ResponseEntity addCar(@RequestBody CarDto car) {
        return null;
    }

    @Override
    @PutMapping("/car/edit/{id}")
    public ResponseEntity editCar(@PathVariable Long id, @RequestBody CarDto carToUpdate) {
        return null;
    }

    @Override
    @DeleteMapping("/car/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        return null;
    }
}

package com.rent.rest_api;

import com.rent.model.dto.CarDto;
import com.rent.model.entity.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface CarController {

    @GetMapping("/car/{id}")
    ResponseEntity getCar(@PathVariable Long id);

    @GetMapping("/car/list")
    List<Car> getAllCars();

    @GetMapping("/car/available")
    List<CarDto> getAvailableCars();

    @PostMapping("/admin/car/add")
    ResponseEntity addCar(@RequestBody CarDto car);

    @PutMapping("/admin/car/edit/{id}")
    ResponseEntity editCar(@PathVariable Long id, @RequestBody CarDto carToUpdate);

    @DeleteMapping("/admin/car/delete/{id}")
    ResponseEntity deleteCar(@PathVariable Long id);
}

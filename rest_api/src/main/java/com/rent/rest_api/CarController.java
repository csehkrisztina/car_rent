package com.rent.rest_api;

import com.rent.model.dto.CarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface CarController {

    @GetMapping("/car/{id}")
    CarDto getCar(@RequestParam Long id);

    @GetMapping("/car/list")
    List<CarDto> getAllCars();

    @PostMapping("/car/add")
    ResponseEntity addCar(@RequestBody CarDto car);

    @PutMapping("/car/edit/{id}")
    ResponseEntity editCar(@PathVariable Long id, @RequestBody CarDto carToUpdate);

    @DeleteMapping("/car/delete/{id}")
    ResponseEntity deleteCar(@PathVariable Long id);
}

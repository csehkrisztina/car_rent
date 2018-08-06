package com.rent.rest_api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface CarController {

    @GetMapping("/car/{id}")
    JSONPObject getCar(@RequestParam Long id);

    @GetMapping("/car/list")
    List<JSONPObject> getAllCars();

    @PostMapping("/car/add")
    ResponseEntity addCar(@RequestBody JSONPObject car);

    @PutMapping("/car/edit/{id}")
    ResponseEntity editCar(@PathVariable Long id);

    @DeleteMapping("/car/delete/{id}")
    ResponseEntity deleteCar(@PathVariable Long id);
}

package com.rent.rest_api_impl;

import com.rent.model.Brand;
import com.rent.model.dto.CarDto;
import com.rent.rest_api.CarController;
import com.rent.service_api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarControllerImpl implements CarController {

    @Autowired
    private CarService carService;

    @Override
    @GetMapping("car/{id}")
    public ResponseEntity getCar(@PathVariable Long id) {
        if(carService.existsCarWithId(id)) {
            carService.getCar(id);
            return new ResponseEntity<CarDto>(carService.getCar(id), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    @GetMapping("car/list")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    /*
    example
    {
        "registNumber": "BH89ERT",
        "fuelType": "DIESEL",
        "price": 47,
        "transmissionType": "MANUAL",
        "brand": "VOLKSWAGEN"
    }
    */

    @Override
    @PostMapping("car/add")
    public ResponseEntity addCar(@RequestBody CarDto car) {
        carService.saveCar(car);
        // poate ar trebui ceva test
        return new ResponseEntity<String>("Car saved", HttpStatus.OK);
    }

    @Override
    @PutMapping("car/edit/{id}")
    public ResponseEntity editCar(@PathVariable Long id, @RequestBody CarDto carToUpdate) {
        if(carService.existsCarWithId(id)) {
            carService.updateCar(id, carToUpdate);
            return new ResponseEntity<String>("Car updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @DeleteMapping("car/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        if(carService.existsCarWithId(id)) {
            carService.deleteCar(id);
            return new ResponseEntity<String>("Car deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }
}

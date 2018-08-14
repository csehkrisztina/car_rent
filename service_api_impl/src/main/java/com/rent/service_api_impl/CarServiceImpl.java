package com.rent.service_api_impl;

import com.rent.model.dto.CarDto;
import com.rent.model.entity.Car;
import com.rent.model.repository.CarRepository;
import com.rent.service_api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public CarDto getCar(Long id) {
        Car car = carRepository.findById(id).get();
        return car.toDto();
    }

    @Override
    public List<CarDto> getAllCars() {
        List<CarDto> cars = new ArrayList<>();
        carRepository.findAll().forEach((car) -> cars.add(car.toDto()));

        return cars;
    }

    @Override
    public List<CarDto> getAvailableCars() {
        List<CarDto> availableCars = new ArrayList<>();
        carRepository.findAll().forEach((car) -> {
            if(car.isAvailable() == true) {
                availableCars.add(car.toDto());
            }
        });

        return availableCars;
    }

    @Override
    public void saveCar(CarDto car) {
        Car c = new Car();
        c.update(car);
        c.setAvailable(true);

        carRepository.save(c);
    }

    @Override
    public void updateCar(Long id, CarDto updatedCar) {
        Car c = carRepository.findById(id).get();
        c.update(updatedCar);

        carRepository.save(c);
    }

    @Override
    public void deleteCar(String registNumber) {
        Car car = carRepository.findByRegistNumber(registNumber);
        carRepository.delete(car);
    }

    @Override
    public boolean existsCarWithId(Long id) {
        return carRepository.findById(id).isPresent();
    }
}

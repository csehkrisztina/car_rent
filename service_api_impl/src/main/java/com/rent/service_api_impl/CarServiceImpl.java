package com.rent.service_api_impl;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.CarDto;
import com.rent.model.entity.CarEntity;
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
        CarEntity car = carRepository.findById(id).get();
        return car.toDto();
    }

    @Override
    public List<CarDto> getAllCars() {
        List<CarDto> cars = new ArrayList<>();
        carRepository.findAll().forEach((car) -> {
            cars.add(car.toDto());
        });
        return cars;
    }

    @Override
    public void saveCar(CarDto car) {
        CarEntity c = new CarEntity();
        c.update(car);
        carRepository.save(c);
    }

    @Override
    public void updateCar(Long id, CarDto updatedCar) {
        CarEntity c = carRepository.findById(id).get();
        c.update(updatedCar);
        carRepository.save(c);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean existsCarWithId(Long id) {
        return carRepository.findById(id).isPresent();
    }

//    @Override
//    public Brand convertStringToBrand(String s) {
//
//    }
//
//    @Override
//    public FuelType convertStringToFuelType(String s) {
//        return null;
//    }
//
//    @Override
//    public TransmissionType convertStringToTransmissionType(String s) {
//        return null;
//    }
}

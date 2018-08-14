package com.rent.service_api;

import com.rent.model.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    CarDto getCar(Long id);

    List<CarDto> getAllCars();

    List<CarDto> getAvailableCars();

    void saveCar(CarDto car);

    void updateCar(Long id, CarDto updatedCar);

    void deleteCar(String registNumber);

    boolean existsCarWithId(Long id);
}

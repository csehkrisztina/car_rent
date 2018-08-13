package com.rent.service_api;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.CarDto;
import com.rent.model.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    CarDto getCar(Long id);

    List<Car> getAllCars();

    List<CarDto> getAvailableCars();

    void saveCar(CarDto car);

    void updateCar(Long id, CarDto updatedCar);

    void deleteCar(Long id);

    boolean existsCarWithId(Long id);
}

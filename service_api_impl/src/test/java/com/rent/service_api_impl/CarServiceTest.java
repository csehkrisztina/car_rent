package com.rent.service_api_impl;

import com.rent.model.dto.CarDto;
import com.rent.model.entity.CarEntity;
import com.rent.model.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    private CarEntity car;

    @Before
    public void SetData() {
        car = new CarEntity();
        car.setBrand("AUDI");
        car.setFuelType("DIESEL");
        car.setRegistNumber("BH78ASD");
        car.setTransmissionType("MANUAL");
        car.setPrice(50);

    }

    @Test
    public void getCar_ExpectsCarDtoObject() {
        Optional<CarEntity> c = Optional.of(car);
        when(carRepository.findById(anyLong())).thenReturn(c);

        CarDto result = carService.getCar(2L);

        CarDto expected = car.toDto();
        assertEquals(expected, result);
    }

    @Test
    public void getAllCars_ExpectsListOfCarDtos() {
        List<CarEntity> cars = new ArrayList<>();
        cars.add(car);
        when(carRepository.findAll()).thenReturn(cars);

        List<CarDto> result = carService.getAllCars();

        List<CarDto> expected = new ArrayList<>();
        expected.add(car.toDto());
        assertEquals(expected, result);
    }

    @Test
    public void saveCar_ExpectsAnObjectAddedInRepository() {
        CarDto c = new CarDto();
        c.setPrice(32);

        carService.saveCar(c);

        assertFalse(carRepository.findAll().size() > 0);
    }
}
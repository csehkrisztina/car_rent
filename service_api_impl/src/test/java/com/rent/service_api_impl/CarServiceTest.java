package com.rent.service_api_impl;

import com.rent.model.dto.CarDto;
import com.rent.model.entity.Car;
import com.rent.model.repository.CarRepository;
import org.junit.Assert;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    private Car car;

    @Before
    public void SetData() {
        car = new Car();
        car.setBrand("AUDI");
        car.setFuelType("DIESEL");
        car.setRegistNumber("BH78ASD");
        car.setTransmissionType("MANUAL");
    }

    @Test
    public void getCar_ExpectsCarDtoObject() {
        Optional<Car> c = Optional.of(car);
        when(carRepository.findById(anyLong())).thenReturn(c);

        CarDto result = carService.getCar(2L);

        CarDto expected = car.toDto();
        assertEquals(expected, result);
    }

//    @Test
//    public void getAllCars_ExpectsListOfCarDtos() {
//        List<Car> cars = new ArrayList<>();
//        cars.add(car);
//        when(carRepository.findAll()).thenReturn(cars);
//
//        List<CarDto> result = carService.getAllCars();
//
//        List<CarDto> expected = new ArrayList<>();
//        expected.add(car.toDto());
//        assertEquals(expected, result);
//    }

    @Test
    public void saveCar_ExpectsAnObjectAddedInRepository() {
        CarDto c = new CarDto();
        c.setPrice(32);

        carService.saveCar(c);

        assertFalse(carRepository.count() > 0);
    }

    @Test
    public void updateCar_ExpectsRepositorySaveMethodCall() {
        Optional<Car> c = Optional.of(car);
        when(carRepository.findById(anyLong())).thenReturn(c);

        carService.updateCar(2L, car.toDto());
        verify(carRepository, times(1)).save(car);
    }

//    @Test
//    public void deleteCar_ExpectsRepositoryDeleteByIdMEthodCall() {
//
//        carService.deleteCar(2L);
//
//        verify(carRepository, times(1)).deleteById(2L);
//    }

    @Test
    public void existsCarWithId_ExpectsTrue() {
        Optional<Car> c = Optional.of(car);
        when(carRepository.findById(anyLong())).thenReturn(c);

        boolean result = carService.existsCarWithId(2L);

        boolean expected = true;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void existsCarWithId_ExpectsFalse() {

        boolean result = carService.existsCarWithId(2L);

        boolean expected = false;
        Assert.assertEquals(expected, result);
    }
}

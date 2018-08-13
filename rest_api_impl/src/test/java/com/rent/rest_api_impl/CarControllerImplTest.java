package com.rent.rest_api_impl;

import com.rent.model.dto.CarDto;
import com.rent.service_api.CarService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarControllerImplTest {

    @InjectMocks
    private CarControllerImpl carController;

    @Mock
    private CarService carService;

    private CarDto car;

    @Before
    public void SetUp() {
        car = new CarDto();
        car.setBrand("AUDI");
        car.setFuelType("DIESEL");
        car.setRegistNumber("BH78ASD");
        car.setTransmissionType("MANUAL");
        car.setPrice(50);
    }

    @Test
    public void getCar_ExpectsHttpStatusOk() {
        when(carService.existsCarWithId(anyLong())).thenReturn(true);
        when(carService.getCar(anyLong())).thenReturn(car);

        ResponseEntity result = carController.getCar(2L);

        ResponseEntity expected = new ResponseEntity<CarDto>(car, HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getCar_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = carController.getCar(2L);

        ResponseEntity expected = new ResponseEntity(HttpStatus.BAD_REQUEST);
        Assert.assertEquals(expected, result);
    }

//    @Test
//    public void getAllCars_ExpectsListOfCars() {
//        List<CarDto> cars = new ArrayList<>();
//        cars.add(car);
//        when(carService.getAllCars()).thenReturn(cars);
//
//        List<CarDto> result = carController.getAllCars();
//
//        Assert.assertEquals(cars, result);
//    }

    @Test
    public void addCar_ExpectsHttpStatusOk() {

        ResponseEntity result = carController.addCar(car);

        ResponseEntity expected = new ResponseEntity<String>("Car saved", HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void addCar_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = carController.addCar(null);

        ResponseEntity expected = new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCar_ExpectsHttpStatusOk() {
        when(carService.existsCarWithId(anyLong())).thenReturn(true);

        ResponseEntity result = carController.editCar(2L, car);

        ResponseEntity expected = new ResponseEntity<String>("Car updated", HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCar_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = carController.editCar(2L, car);

        ResponseEntity expected = new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteCar_ExpectsHttpStatusOk() {
        when(carService.existsCarWithId(anyLong())).thenReturn(true);

        ResponseEntity result = carController.deleteCar(2L);

        ResponseEntity expected = new ResponseEntity<String>("Car deleted", HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteCar_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = carController.deleteCar(2L);

        ResponseEntity expected = new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(expected, result);
    }
}

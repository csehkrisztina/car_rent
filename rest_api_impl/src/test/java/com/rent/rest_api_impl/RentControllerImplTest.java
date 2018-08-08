package com.rent.rest_api_impl;

import com.rent.model.dto.RentDto;
import com.rent.service_api.CarService;
import com.rent.service_api.RentService;
import com.rent.service_api.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentControllerImplTest {

    @InjectMocks
    private RentControllerImpl rentController;

    @Mock
    private RentService rentService;

    @Mock
    private CarService carService;

    @Mock
    private UserService userService;

    private RentDto rent;

    @Before
    public void SetUp() {
        rent = new RentDto();
        rent.setUserId(2L);
        rent.setCarId(2L);
    }

    @Test
    public void rentACar_ExpectsHttpStatusOk() {
        when(carService.existsCarWithId(anyLong())).thenReturn(true);
        when(userService.existsUserWithId(anyLong())).thenReturn(true);

        ResponseEntity result = rentController.rentACar(rent);

        ResponseEntity expected = new ResponseEntity<String>("Rent saved", HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void rentACar_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = rentController.rentACar(rent);

        ResponseEntity expected = new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(expected, result);
    }
}

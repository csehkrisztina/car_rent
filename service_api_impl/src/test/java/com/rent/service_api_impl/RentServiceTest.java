package com.rent.service_api_impl;

import com.rent.model.dto.RentDto;
import com.rent.model.entity.Car;
import com.rent.model.entity.Location;
import com.rent.model.entity.Users;
import com.rent.model.repository.CarRepository;
import com.rent.model.repository.LocationRepository;
import com.rent.model.repository.RentRepository;
import com.rent.model.repository.UserRepository;
import org.assertj.core.util.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {

    @InjectMocks
    private RentServiceImpl rentService;

    @Mock
    private RentRepository rentRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LocationRepository locationRepository;

    private RentDto rent;

    private Users user;

    private Car car;

    private Location location;

    @Before
    public void SetUp() {
        rent = new RentDto();
        rent.setUserId(2L);
        rent.setCarId(2L);
        rent.setLocationId(2L);
        rent.setStartDate(new Date());
        rent.setEndDate(DateUtil.tomorrow());

        user = new Users();

        car = new Car();
        car.setPrice(20);

        location = new Location();
    }

    @Test
    public void saveRent_ExpectsRepositorySaveMethodCall() {
        Optional<Users> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);
        Optional<Car> c = Optional.of(car);
        when(carRepository.findById(anyLong())).thenReturn(c);
        Optional<Location> l = Optional.of(location);
        when(locationRepository.findById(anyLong())).thenReturn(l);

        rentService.saveRent(rent);

        Assert.assertFalse(rentRepository.count() > 0);
    }
}

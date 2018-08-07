package com.rent.service_api_impl;

import com.rent.model.dto.LocationDto;
import com.rent.model.dto.RentDto;
import com.rent.model.entity.CarEntity;
import com.rent.model.entity.LocationEntity;
import com.rent.model.entity.RentEntity;
import com.rent.model.entity.UserEntity;
import com.rent.model.repository.CarRepository;
import com.rent.model.repository.LocationRepository;
import com.rent.model.repository.RentRepository;
import com.rent.model.repository.UserRepository;
import com.rent.service_api.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void saveRent(RentDto rent) {
        RentEntity r = new RentEntity();

        r.update(rent);
        r.setUser(getUserById(rent.getUserId()));

        setCarUsed(rent.getCarId());

        r.setCar(getCarById(rent.getCarId()));
        r.setLocation(getLocationById(rent.getLocationId()));

        float price = getPriceDependingOnDateInterval(
                rent.getStartDate(),
                rent.getEndDate(),
                getCarPriceById(rent.getCarId()));
        r.setPrice(price);

        rentRepository.save(r);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public CarEntity getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public LocationEntity getLocationById(Long id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public float getCarPriceById(Long id) {
        CarEntity c = carRepository.findById(id).get();
        return c.getPrice();
    }

    @Override
    public float getPriceDependingOnDateInterval(Date startDate, Date endDate, float pricePerDay) {
        int days = daysBetweenTwoDate(startDate, endDate);
        return days * pricePerDay;
    }

    @Override
    public int daysBetweenTwoDate(Date d1, Date d2) {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    @Override
    public void setCarUsed(Long id) {
        CarEntity car = carRepository.findById(id).get();
        car.setStatus(false);

        carRepository.save(car);
    }
}

package com.rent.service_api_impl;

import com.rent.model.dto.RentDto;
import com.rent.model.entity.Car;
import com.rent.model.entity.Location;
import com.rent.model.entity.Rent;
import com.rent.model.entity.Users;
import com.rent.model.repository.CarRepository;
import com.rent.model.repository.LocationRepository;
import com.rent.model.repository.RentRepository;
import com.rent.model.repository.UserRepository;
import com.rent.service_api.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        Rent r = new Rent();

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
    public Users getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public float getCarPriceById(Long id) {
        Car c = carRepository.findById(id).get();
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
        Car car = carRepository.findById(id).get();
        car.setAvailable(false);

        carRepository.save(car);
    }
}

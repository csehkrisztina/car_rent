package com.rent.service_api_impl;

import com.rent.model.dto.RentDto;
import com.rent.model.entity.CarEntity;
import com.rent.model.entity.RentEntity;
import com.rent.model.repository.CarRepository;
import com.rent.model.repository.RentRepository;
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

    @Override
    public void saveRent(RentDto rent) {
        RentEntity r = new RentEntity();
        r.update(rent);

        CarEntity car = carRepository.findById(rent.getCarId()).get();

        float price = getPriceDependingOnDateInterval(rent.getStartDate(), rent.getEndDate(), car.getPrice());
        r.setPrice(price);

        rentRepository.save(r);
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
}

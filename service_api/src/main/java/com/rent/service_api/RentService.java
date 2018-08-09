package com.rent.service_api;

import com.rent.model.dto.RentDto;
import com.rent.model.entity.Car;
import com.rent.model.entity.Location;
import com.rent.model.entity.Users;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public interface RentService {

    void saveRent(RentDto rent);

    Users getUserById(Long id);

    Car getCarById(Long id);

    Location getLocationById(Long id);

    float getCarPriceById(Long id);

    float getPriceDependingOnDateInterval(Date startDate, Date endDate, float pricePerDay);

    int daysBetweenTwoDate(Date d1, Date d2);

    void setCarUsed(Long id);
}

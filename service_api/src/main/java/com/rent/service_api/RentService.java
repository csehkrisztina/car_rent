package com.rent.service_api;

import com.rent.model.dto.LocationDto;
import com.rent.model.dto.RentDto;
import com.rent.model.entity.CarEntity;
import com.rent.model.entity.LocationEntity;
import com.rent.model.entity.UserEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public interface RentService {

    void saveRent(RentDto rent);

    UserEntity getUserById(Long id);

    CarEntity getCarById(Long id);

    LocationEntity getLocationById(Long id);

    float getCarPriceById(Long id);

    float getPriceDependingOnDateInterval(Date startDate, Date endDate, float pricePerDay);

    int daysBetweenTwoDate(Date d1, Date d2);

    void setCarUsed(Long id);
}

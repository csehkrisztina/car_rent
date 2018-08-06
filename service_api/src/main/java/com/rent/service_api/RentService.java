package com.rent.service_api;

import com.rent.model.dto.RentDto;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public interface RentService {

    void saveRent(RentDto rent);

    float getPriceDependingOnDateInterval(Date startDate, Date endDate, float pricePerDay);

    int daysBetweenTwoDate(Date d1, Date d2);
}

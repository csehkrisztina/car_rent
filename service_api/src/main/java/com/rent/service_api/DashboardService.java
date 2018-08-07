package com.rent.service_api;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.LocationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public interface DashboardService {

    List<LocationDto> getAllLocations();

    List<Brand> getAllBrands();

    List<FuelType> getAllFuelTypes();

    List<TransmissionType> getAllTransmissionTypes();
}

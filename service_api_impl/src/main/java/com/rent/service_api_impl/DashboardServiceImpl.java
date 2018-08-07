package com.rent.service_api_impl;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.LocationDto;
import com.rent.model.repository.LocationRepository;
import com.rent.service_api.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationDto> getAllLocations() {
        List<LocationDto> locations = new ArrayList<>();
        locationRepository.findAll().forEach((location) -> {
            locations.add(location.toDto());
        });

        return locations;
    }

    @Override
    public List<Brand> getAllBrands() {
        return Arrays.asList(Brand.values());
    }

    @Override
    public List<FuelType> getAllFuelTypes() {
        return Arrays.asList(FuelType.values());
    }

    @Override
    public List<TransmissionType> getAllTransmissionTypes() {
        return Arrays.asList(TransmissionType.values());
    }
}

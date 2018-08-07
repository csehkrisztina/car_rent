package com.rent.rest_api_impl;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.LocationDto;
import com.rent.model.repository.LocationRepository;
import com.rent.rest_api.DashboardController;
import com.rent.service_api.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DashboardControllerImpl implements DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Override
    public List<LocationDto> getLocations() {
        return dashboardService.getAllLocations();
    }

    @Override
    public List<Brand> getBrands() {
        return dashboardService.getAllBrands();
    }

    @Override
    public List<FuelType> getFuelTypes() {
        return dashboardService.getAllFuelTypes();
    }

    @Override
    public List<TransmissionType> getTransmissionTypes() {
        return dashboardService.getAllTransmissionTypes();
    }
}

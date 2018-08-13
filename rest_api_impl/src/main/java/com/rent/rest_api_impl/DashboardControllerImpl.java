package com.rent.rest_api_impl;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.LocationDto;
import com.rent.model.repository.LocationRepository;
import com.rent.rest_api.DashboardController;
import com.rent.service_api.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class DashboardControllerImpl implements DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Override
    @GetMapping("/locations")
    public List<LocationDto> getLocations() {
        return dashboardService.getAllLocations();
    }

    @Override
    @GetMapping("/brands")
    public List<Brand> getBrands() {
        return dashboardService.getAllBrands();
    }

    @Override
    @GetMapping("/fuel-types")
    public List<FuelType> getFuelTypes() {
        return dashboardService.getAllFuelTypes();
    }

    @Override
    @GetMapping("/transmission-types")
    public List<TransmissionType> getTransmissionTypes() {
        return dashboardService.getAllTransmissionTypes();
    }
}

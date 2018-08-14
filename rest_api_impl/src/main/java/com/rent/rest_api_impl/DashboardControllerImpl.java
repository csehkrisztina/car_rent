package com.rent.rest_api_impl;

import com.rent.model.dto.LocationDto;
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
}

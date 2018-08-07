package com.rent.rest_api;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.LocationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface DashboardController {

    @GetMapping("/locations")
    List<LocationDto> getLocations();

    @GetMapping("/brands")
    List<Brand> getBrands();

    @GetMapping("fuel-types")
    List<FuelType> getFuelTypes();

    @GetMapping("transmission-types")
    List<TransmissionType> getTransmissionTypes();
}

package com.rent.rest_api;

import com.rent.model.dto.LocationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface DashboardController {

    @GetMapping("/locations")
    List<LocationDto> getLocations();
}

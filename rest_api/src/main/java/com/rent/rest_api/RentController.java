package com.rent.rest_api;

import com.rent.model.dto.LocationDto;
import com.rent.model.dto.RentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface RentController {

    @PostMapping("/rent")
    ResponseEntity rentACar(@RequestBody RentDto rent);

    @GetMapping("/locations")
    List<LocationDto> getAllLocations();
}

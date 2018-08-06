package com.rent.rest_api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface RentController {

    @PostMapping("/rent")
    ResponseEntity rentACar(@RequestBody JSONPObject rent);
}

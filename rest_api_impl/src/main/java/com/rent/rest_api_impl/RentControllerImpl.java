package com.rent.rest_api_impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rent.rest_api.RentController;
import com.rent.service_api.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentControllerImpl implements RentController {

    @Autowired
    private RentService rentService;

    @Override
    @PostMapping("/rent")
    public ResponseEntity rentACar(@RequestBody JSONPObject rent) {
        return null;
    }
}

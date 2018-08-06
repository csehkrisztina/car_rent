package com.rent.rest_api_impl;

import com.rent.model.dto.RentDto;
import com.rent.rest_api.RentController;
import com.rent.service_api.CarService;
import com.rent.service_api.RentService;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentControllerImpl implements RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Override
    @PostMapping("rent")
    public ResponseEntity rentACar(@RequestBody RentDto rent) {
        if(carService.existsCarWithId(rent.getCarId()) && userService.existsUserWithId(rent.getUserId())) {
            rentService.saveRent(rent);
            return new ResponseEntity<String>("Rent saved", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }
}

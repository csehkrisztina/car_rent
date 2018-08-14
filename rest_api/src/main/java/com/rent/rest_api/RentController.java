package com.rent.rest_api;

import com.rent.model.dto.RentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public interface RentController {

    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    ModelAndView rentACar();

    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    ModelAndView rentACar(@RequestBody RentDto rent);
}

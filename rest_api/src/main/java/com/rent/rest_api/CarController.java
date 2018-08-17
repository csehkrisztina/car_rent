package com.rent.rest_api;

import com.rent.model.dto.CarDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public interface CarController {

    @RequestMapping(value = "/car/available", method = RequestMethod.GET)
    List<CarDto> getAvailableCars();

    @RequestMapping(value = "/admin/car/add", method = RequestMethod.GET)
    ModelAndView addCar();

    @RequestMapping(value = "/admin/car/add", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    ModelAndView addCar(@ModelAttribute CarDto car);

    @RequestMapping(value = "/admin/car/all", method = RequestMethod.GET)
    ModelAndView getAllCars();

    @RequestMapping(value = "/admin/car/edit", method = RequestMethod.GET)
    ModelAndView editCar(@RequestParam String registNumber);

    @RequestMapping(value = "/admin/car/edit", method = RequestMethod.POST)
    ModelAndView editCar(@ModelAttribute CarDto updatedCar);

    @DeleteMapping("/admin/car/delete")
    ModelAndView deleteCar(@RequestParam String registNumber);
}

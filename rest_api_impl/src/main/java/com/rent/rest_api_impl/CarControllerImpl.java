package com.rent.rest_api_impl;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.CarDto;
import com.rent.model.entity.Users;
import com.rent.rest_api.CarController;
import com.rent.service_api.CarService;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@RestController
public class CarControllerImpl implements CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/admin/car/all")
    public ModelAndView getAllCars() {
        List<CarDto> cars = carService.getAllCars();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cars", cars);
        modelAndView.setViewName("admin/cars");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/car/edit", method = RequestMethod.GET)
    public ModelAndView editCar(@RequestParam String registNumber) {
        CarDto car = carService.getCarByRegistNumber(registNumber);

        List<Brand> brands = Arrays.asList(Brand.values());
        List<TransmissionType> transmissionTypes = Arrays.asList(TransmissionType.values());
        List<FuelType> fuelTypes = Arrays.asList(FuelType.values());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", car);

        modelAndView.addObject("brands", brands);
        modelAndView.addObject("transmissionTypes", transmissionTypes);
        modelAndView.addObject("fuelTypes", fuelTypes);

        modelAndView.setViewName("admin/car-add-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/car/edit", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public ModelAndView editCar(@ModelAttribute CarDto updatedCar) {
        carService.updateCar(updatedCar.getRegistNumber(), updatedCar);

        List<Brand> brands = Arrays.asList(Brand.values());
        List<TransmissionType> transmissionTypes = Arrays.asList(TransmissionType.values());
        List<FuelType> fuelTypes = Arrays.asList(FuelType.values());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", new CarDto());

        modelAndView.addObject("brands", brands);
        modelAndView.addObject("transmissionTypes", transmissionTypes);
        modelAndView.addObject("fuelTypes", fuelTypes);

        modelAndView.addObject("successMessage", "The car has been saved successfully");
        modelAndView.setViewName("admin/car-add-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "car/available", method = RequestMethod.GET)
    public List<CarDto> getAvailableCars() {
        return null;
    }

    @Override
    @RequestMapping(value = "/admin/car/add", method = RequestMethod.GET)
    public ModelAndView addCar() {
        ModelAndView modelAndView = new ModelAndView();

        List<Brand> brands = Arrays.asList(Brand.values());
        List<TransmissionType> transmissionTypes = Arrays.asList(TransmissionType.values());
        List<FuelType> fuelTypes = Arrays.asList(FuelType.values());

        modelAndView.addObject("car", new CarDto());

        modelAndView.addObject("brands", brands);
        modelAndView.addObject("transmissionTypes", transmissionTypes);
        modelAndView.addObject("fuelTypes", fuelTypes);

        modelAndView.setViewName("admin/car-add-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/car/add", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public ModelAndView addCar(@ModelAttribute CarDto car) {
        carService.saveCar(car);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", new CarDto());
        modelAndView.addObject("successMessage", "The car has been saved successfully");
        modelAndView.setViewName("admin/car-add-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/car/delete", method = RequestMethod.GET)
    public ModelAndView deleteCar(@RequestParam String registNumber) {
        carService.deleteCar(registNumber);

        List<CarDto> cars = carService.getAllCars();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("message", "The car has been deleted successfully!");
        modelAndView.setViewName("admin/cars");

        return modelAndView;
    }
}

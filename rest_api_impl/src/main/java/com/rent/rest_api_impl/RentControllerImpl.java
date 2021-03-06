package com.rent.rest_api_impl;

import com.rent.model.dto.RentDto;
import com.rent.rest_api.RentController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RentControllerImpl implements RentController {

    @Override
    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    public ModelAndView rentACar() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("rent");
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    public ModelAndView rentACar(RentDto rent) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("rent");
        return modelAndView;
    }
}

package com.rent.rest_api;

import com.rent.model.dto.UserDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public interface LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    ModelAndView login();

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    ModelAndView registration();

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    ModelAndView registration(@Valid UserDto user, BindingResult bindingResult);

    @RequestMapping(value="/admin/dashboard", method = RequestMethod.GET)
    ModelAndView admin_dashboard();

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    ModelAndView home(Principal principal);
}

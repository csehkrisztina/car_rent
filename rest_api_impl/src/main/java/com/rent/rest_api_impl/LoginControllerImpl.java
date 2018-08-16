package com.rent.rest_api_impl;

import com.rent.model.dto.CarDto;
import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import com.rent.rest_api.LoginController;
import com.rent.service_api.CarService;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LoginControllerImpl implements LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", new Users());
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@Valid UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Users userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (!bindingResult.hasErrors()) {
            userService.saveUser(user);

            modelAndView.addObject("user", new Users());
            modelAndView.addObject("successMessage", "User has been registered successfully");
        }
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<CarDto> cars = carService.getAllCars();

//        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
//        boolean isUserLoggedIn = (auth.getPrincipal() == null) ? false : true;

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("cars", cars);
//        modelAndView.addObject("isAdmin", isAdmin);
//        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.setViewName("home");

        return modelAndView;
    }
}

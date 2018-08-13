package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Car;
import com.rent.model.entity.Users;
import com.rent.rest_api.LoginController;
import com.rent.service_api.CarService;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
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

        Users user = new Users();
        modelAndView.addObject("user", user);
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

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);

            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Users());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @Override
    @RequestMapping(value="/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView admin_dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/dashboard");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Car> cars = carService.getAllCars();

        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
        boolean isUserLoggedIn = (principal == null) ? false : true;

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("cars", cars);
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.setViewName("home");

        return modelAndView;
    }
}

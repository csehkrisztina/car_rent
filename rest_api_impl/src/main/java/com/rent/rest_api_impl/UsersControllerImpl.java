package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import com.rent.rest_api.UsersController;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UsersControllerImpl implements UsersController {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(value = "/my-account", method = RequestMethod.GET)
    public ModelAndView editUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUserByEmail(auth.getName());

        boolean isAdmin = userService.isAdmin();
        boolean isUserLoggedIn = userService.isLoggedInUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.setViewName("my-account");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/my-account", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public ModelAndView editUser(@ModelAttribute UserDto updatedUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUserByEmail(auth.getName());

        userService.updateUser(user.getId(), updatedUser);

        boolean isAdmin = userService.isAdmin();
        boolean isUserLoggedIn = userService.isLoggedInUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.findUserByEmail(updatedUser.getEmail()));
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.addObject("successMessage", "The user has been updated successfully");
        modelAndView.setViewName("my-account");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<UserDto> users = userService.getAllUsers();

        boolean isAdmin = userService.isAdmin();
        boolean isUserLoggedIn = userService.isLoggedInUser();

        List<String> roles = (List) auth.getAuthorities();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.addObject("role", roles.get(0));
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.setViewName("users");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable String email) {
        userService.deleteUser(email);

        boolean isAdmin = userService.isAdmin();
        boolean isUserLoggedIn = userService.isLoggedInUser();

        List<UserDto> users = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.addObject("message", "User with e-mail '" + email + "' was deleted");
        modelAndView.setViewName("users");

        return modelAndView;
    }
}

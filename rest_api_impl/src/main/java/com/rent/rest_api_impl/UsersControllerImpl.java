package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import com.rent.rest_api.UsersController;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public ModelAndView editUser(@RequestParam(value = "email", required = false) String email) {
        String title = "Edit '" + email + "' account";

        if(email == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            email = auth.getName();
            title = "Edit my account";
        }

        Users user = userService.findUserByEmail(email);

        boolean isAdmin = userService.isAdmin();
        boolean isUserLoggedIn = userService.isLoggedInUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.addObject("title", title);
        modelAndView.setViewName("user-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public ModelAndView editUser(@ModelAttribute UserDto updatedUser) {
        Users user = userService.findUserByEmail(updatedUser.getEmail());

        userService.updateUser(user.getId(), updatedUser);

        boolean isAdmin = userService.isAdmin();
        boolean isUserLoggedIn = userService.isLoggedInUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.findUserByEmail(updatedUser.getEmail()));
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.addObject("successMessage", "The user has been updated successfully");
        modelAndView.setViewName("user-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/user/all", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        List<UserDto> users = userService.getAllUsers();

        boolean isAdmin = userService.isAdmin();
        boolean isUserLoggedIn = userService.isLoggedInUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isLoggedIn", isUserLoggedIn);
        modelAndView.setViewName("admin/users");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam String email) {
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

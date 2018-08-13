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
        return null;
    }

    @Override
    @DeleteMapping("/admin/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if(userService.existsUserWithId(id)) {
            userService.deleteUser(id);
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }
}

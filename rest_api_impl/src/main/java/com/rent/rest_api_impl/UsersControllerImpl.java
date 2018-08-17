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
    public ModelAndView editUser() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Users user = userService.findUserByEmail(email);

        modelAndView.addObject("user", user);
        modelAndView.addObject("title", "Edit my account");
        modelAndView.addObject("action", "/user/edit");
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public ModelAndView editUser(@ModelAttribute UserDto updatedUser) {
        Users user = userService.findUserByEmail(updatedUser.getEmail());

        userService.updateUser(user.getId(), updatedUser);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", userService.findUserByEmail(updatedUser.getEmail()));
        modelAndView.addObject("title", "Edit my account");
        modelAndView.addObject("successMessage", "The user has been updated successfully");
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/user/all", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        List<UserDto> users = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin/users");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam String email) {
        userService.deleteUser(email);

        List<UserDto> users = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.addObject("successMessage", "User with e-mail '" + email + "' has been deleted successfully!");
        modelAndView.setViewName("admin/users");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    public ModelAndView adminEditUser(@RequestParam String email) {
        Users user = userService.findUserByEmail(email);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/user-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public ModelAndView adminEditUser(@ModelAttribute UserDto updatedUser) {
        Users user = userService.findUserByEmail(updatedUser.getEmail());

        userService.updateUser(user.getId(), updatedUser);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", userService.findUserByEmail(updatedUser.getEmail()));
        modelAndView.addObject("successMessage", "The user has been updated successfully");
        modelAndView.setViewName("admin/user-edit");

        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/admin/panel", method = RequestMethod.GET)
    public ModelAndView adminPanel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin-panel");

        return modelAndView;
    }
}

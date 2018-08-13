package com.rent.rest_api;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public interface UsersController {

    @RequestMapping(value = "/my-account", method = RequestMethod.GET)
    ModelAndView editUser();

    @RequestMapping(value = "/my-account", method = RequestMethod.POST)
    ModelAndView editUser(@RequestBody UserDto updatedUser);

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    ModelAndView getUsers();

    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    ModelAndView deleteUser(@PathVariable String email);
}

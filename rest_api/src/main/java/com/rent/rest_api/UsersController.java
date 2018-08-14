package com.rent.rest_api;

import com.rent.model.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public interface UsersController {

    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    ModelAndView editUser(@RequestParam String email);

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    ModelAndView editUser(@RequestBody UserDto updatedUser);

    @RequestMapping(value = "/admin/user/all", method = RequestMethod.GET)
    ModelAndView getUsers();

    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    ModelAndView deleteUser(@PathVariable String email);
}

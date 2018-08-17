package com.rent.rest_api;

import com.rent.model.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public interface UsersController {

    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    ModelAndView editUser();

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    ModelAndView editUser(@ModelAttribute UserDto updatedUser);

    @RequestMapping(value = "/admin/user/all", method = RequestMethod.GET)
    ModelAndView getUsers();

    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    ModelAndView deleteUser(@PathVariable String email);

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    ModelAndView adminEditUser(@RequestParam String email);

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    ModelAndView adminEditUser(@ModelAttribute UserDto updatedUser);

    @RequestMapping(value = "/admin/panel", method = RequestMethod.GET)
    ModelAndView adminPanel();
}

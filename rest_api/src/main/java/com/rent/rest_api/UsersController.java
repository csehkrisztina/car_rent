package com.rent.rest_api;

import com.rent.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public interface UsersController {

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    ModelAndView login();

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    ModelAndView registration();

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    ModelAndView createNewUser(@Valid UserDto user, BindingResult bindingResult);

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    ModelAndView home();

//    ------------------------------------------------------

//    @GetMapping("/login")
//    String login(HttpServletRequest request);
//
//    @GetMapping("/logout")
//    String logout(HttpServletResponse response, HttpServletRequest request);
//
//    @GetMapping("/user")
//    UserDto getLoggedUser();
//
//    @PostMapping("/user/add")
//    ResponseEntity addUser(@RequestBody UserDto user);
//
//    @PutMapping("/user/edit/{id}")
//    ResponseEntity editUser(@PathVariable("id") Long id, @RequestBody UserDto updatedUser);
//
//    @DeleteMapping("/admin/user/delete/{id}")
//    ResponseEntity deleteUser(@PathVariable("id") Long id);
}

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

    @PostMapping("/user/add")
    ResponseEntity addUser(@RequestBody UserDto user);

    @PutMapping("/user/edit/{id}")
    ResponseEntity editUser(@PathVariable("id") Long id, @RequestBody UserDto updatedUser);

    @DeleteMapping("/admin/user/delete/{id}")
    ResponseEntity deleteUser(@PathVariable("id") Long id);
}

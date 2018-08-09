package com.rent.rest_api;

import com.rent.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public interface UsersController {

    @GetMapping("/login")
    String login(HttpServletRequest request);

    @GetMapping("/logout")
    String logout(HttpServletResponse response, HttpServletRequest request);

    @GetMapping("/user")
    UserDto getLoggedUser();

    @PostMapping("/user/add")
    ResponseEntity addUser(@RequestBody UserDto user);

    @PutMapping("/user/edit/{id}")
    ResponseEntity editUser(@PathVariable("id") Long id, @RequestBody UserDto updatedUser);

    @DeleteMapping("/admin/user/delete/{id}")
    ResponseEntity deleteUser(@PathVariable("id") Long id);
}

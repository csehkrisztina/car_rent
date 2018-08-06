package com.rent.rest_api;

import com.rent.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface UserController {

    @PostMapping("user/add")
    ResponseEntity addUser(@RequestBody UserDto user);

    @PutMapping("user/edit/{id}")
    ResponseEntity editUser(@PathVariable("id") Long id, @RequestBody UserDto updatedUser);

    @DeleteMapping("user/delete/{id}")
    ResponseEntity deleteUser(@PathVariable("id") Long id);

    @PostMapping("/login")
    ResponseEntity login();
}

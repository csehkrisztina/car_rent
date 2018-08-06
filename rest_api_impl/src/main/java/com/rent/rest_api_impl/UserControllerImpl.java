package com.rent.rest_api_impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rent.rest_api.UserController;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping("user/add")
    public ResponseEntity addUser(@RequestBody JSONPObject user) {
        return null;
    }

    @Override
    @PutMapping("user/edit/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @RequestBody JSONPObject updatedUser) {
        return null;
    }

    @Override
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return null;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity login() {
        return null;
    }
}

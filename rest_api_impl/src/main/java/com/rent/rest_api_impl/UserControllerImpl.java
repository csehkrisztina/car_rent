package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.login.Login;
import com.rent.rest_api.UserController;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    /*
    example
    {
        "firstName": "first",
        "lastName": "last",
        "identNumber": "aefneklgnlseingl",
        "age": 34,
        "userName": "f_l"
    }
     */
    @Override
    @PostMapping("user/add")
    public ResponseEntity addUser(@RequestBody UserDto user) {
        if(user != null) {
            userService.saveUser(user);
            return new ResponseEntity<String>("User added", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @PutMapping("user/edit/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        if(userService.existsUserWithId(id)) {
            userService.updateUser(id, updatedUser);
            return new ResponseEntity<String>("User updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if(userService.existsUserWithId(id)) {
            userService.deleteUser(id);
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @PostMapping("login")
    public ResponseEntity login(@RequestBody Login loginData) {
        return null;
    }
}

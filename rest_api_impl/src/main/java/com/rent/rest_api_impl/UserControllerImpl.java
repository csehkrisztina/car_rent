package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
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
            userService.addUser(user);
            return new ResponseEntity<String>("User added", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @PutMapping("user/edit/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        return null;
    }

    @Override
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if(id != null) { // aici trebuie o functie care valideaza id-ul daca exista
            userService.deleteUser(id);
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity login() {
        return null;
    }
}

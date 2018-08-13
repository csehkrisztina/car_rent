package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import com.rent.rest_api.UsersController;
import com.rent.service_api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UsersControllerImpl implements UsersController {


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
    @PostMapping("/user/add")
    public ResponseEntity addUser(@RequestBody UserDto user) {
        if(user != null) {
            userService.saveUser(user);
            return new ResponseEntity<String>("User added", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @PutMapping("/user/edit/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        if(userService.existsUserWithId(id)) {
            userService.updateUser(id, updatedUser);
            return new ResponseEntity<String>("User updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @Override
    @DeleteMapping("/admin/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if(userService.existsUserWithId(id)) {
            userService.deleteUser(id);
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }
}

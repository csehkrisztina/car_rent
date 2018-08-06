package com.rent.rest_api_impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rent.rest_api.UserController;
import org.springframework.http.ResponseEntity;

public class UserControllerImpl implements UserController {

    @Override
    public ResponseEntity addUser(JSONPObject user) {
        return null;
    }

    @Override
    public ResponseEntity editUser(Long id, JSONPObject updatedUser) {
        return null;
    }

    @Override
    public ResponseEntity deleteUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity login() {
        return null;
    }
}

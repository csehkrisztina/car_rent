package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.service_api.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerImplTest {

    @InjectMocks
    private UserControllerImpl userController;

    @Mock
    private UserService userService;

    private UserDto user;

    @Before
    public void SetUp() {
        user = new UserDto();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setIdentNumber("2457815488755");
        user.setAge(20);
        user.setUserName("firstName.lastName");
    }

    @Test
    public void addUser_ExpectsHttpStatusOk() {

        ResponseEntity result = userController.addUser(user);

        ResponseEntity expected = new ResponseEntity<String>("User added", HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void addUser_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = userController.addUser(null);

        ResponseEntity expected = new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void editUser_ExpectsHttpStatusOk() {
        when(userService.existsUserWithId(anyLong())).thenReturn(true);

        ResponseEntity result = userController.editUser(2L, user);

        ResponseEntity expected = new ResponseEntity<String>("User updated", HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void editUser_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = userController.editUser(2L, user);

        ResponseEntity expected = new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteUser_ExpectsHttpStatusOk() {
        when(userService.existsUserWithId(anyLong())).thenReturn(true);

        ResponseEntity result = userController.deleteUser(2L);

        ResponseEntity expected = new ResponseEntity<String>("User deleted", HttpStatus.OK);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteUser_ExpectsHttpStatusBadRequest() {

        ResponseEntity result = userController.deleteUser(2L);

        ResponseEntity expected = new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(expected, result);
    }
}

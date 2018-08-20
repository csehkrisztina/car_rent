package com.rent.service_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import com.rent.model.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private Users user;

    @Before
    public void SetUp() {
        user = new Users();
        user.setId(2L);
        user.setEmail("test@yahoo.com");
    }

    @Test
    public void saveUser_ExpectsRepositorySizeInscrease() {

        userService.saveUser(user.toDto());

        Assert.assertFalse(userRepository.count() > 0);
    }

    @Test
    public void updateUser_ExpectsRepositorySaveMethodCall() {
        Optional<Users> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);

        userService.updateUser(2L, user.toDto());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void deleteUser_ExpectsRepositoryDeleteByIdMethodCall() {
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        userService.deleteUser(user.getEmail());

        verify(userRepository, times(1)).deleteById(2L);
    }

    @Test
    public void getUser_ExpectsuserDtoObject() {
        Optional<Users> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);

        UserDto result = userService.getUser(2L);

        UserDto expected = user.toDto();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getAllUsers_ExpectsListOfUserDto() {
        List<Users> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> result = userService.getAllUsers();

        List<UserDto> expected = new ArrayList<>();
        expected.add(user.toDto());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void existsUserWithId_ExpectsTrue() {
        Optional<Users> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);

        boolean result = userService.existsUserWithId(2L);

        boolean expected = true;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void existsUserWithId_ExpectsFalse() {

        boolean result = userService.existsUserWithId(2L);

        boolean expected = false;
        Assert.assertEquals(expected, result);
    }
}

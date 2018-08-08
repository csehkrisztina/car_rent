package com.rent.service_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.UserEntity;
import com.rent.model.repository.UserRepository;
import com.rent.service_api.UserService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private UserEntity user;

    @Before
    public void SetUp() {
        user = new UserEntity();

    }

    @Test
    public void saveUser_ExpectsRepositorySizeInscrease() {

        userService.saveUser(user.toDto());

        Assert.assertFalse(userRepository.count() > 0);
    }

    @Test
    public void updateUser_ExpectsRepositorySaveMethodCall() {
        Optional<UserEntity> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);

        userService.updateUser(2L, user.toDto());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void deleteUser_ExpectsRepositoryDeleteByIdMethodCall() {

        userService.deleteUser(2L);

        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void getUser_ExpectsuserDtoObject() {
        Optional<UserEntity> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);

        UserDto result = userService.getUser(2L);

        UserDto expected = user.toDto();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getAllUsers_ExpectsListOfUserDto() {
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> result = userService.getAllUsers();

        List<UserDto> expected = new ArrayList<>();
        expected.add(user.toDto());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void existsUserWithId_ExpectsTrue() {
        Optional<UserEntity> u = Optional.of(user);
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

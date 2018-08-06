package com.rent.service_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.UserEntity;
import com.rent.model.repository.UserRepository;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserDto user) {
        UserEntity u = new UserEntity();
        u.update(user);
        userRepository.save(u);
    }

    @Override
    public void updateUser(Long id, UserDto userToUpdate) {
        UserEntity u = userRepository.findById(id).get();
        u.update(userToUpdate);
        userRepository.save(u);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUser(Long id) {
        UserEntity u = userRepository.findById(id).get();
        return u.toDto();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach((user)-> {
            users.add(user.toDto());
        });

        return users;
    }
}

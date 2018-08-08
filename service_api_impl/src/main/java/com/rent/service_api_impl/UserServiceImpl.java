package com.rent.service_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.RoleEntity;
import com.rent.model.entity.UserEntity;
import com.rent.model.repository.RoleRepository;
import com.rent.model.repository.UserRepository;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveUser(UserDto user) {
        UserEntity u = new UserEntity();
        u.update(user);
        // u.setRole(roleRepository.findById(2L).get());

        userRepository.save(u);
    }

    @Override
    public void updateUser(Long id, UserDto updatedUser) {
        UserEntity u = userRepository.findById(id).get();
        u.update(updatedUser);

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

    @Override
    public void changeUserRole(Long userId, String role) {
        UserEntity u = userRepository.findById(userId).get();
        RoleEntity r = roleRepository.findByRole(role);
        // u.setRole(r);

        userRepository.save(u);
    }

    @Override
    public boolean existsUserWithId(Long id) {
        return userRepository.findById(id).isPresent();
    }

    @Override
    public List<String> getRoles(Long id) {
        return roleRepository.findRolesByUserId(id);
    }
}

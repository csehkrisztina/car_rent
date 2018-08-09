package com.rent.service_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Role;
import com.rent.model.entity.Users;
import com.rent.model.repository.RoleRepository;
import com.rent.model.repository.UserRepository;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveUser(UserDto user) {
        Users u = new Users();
        u.update(user);
//        u.setRole(roleRepository.findById(2L).get());

        userRepository.save(u);
    }

    @Override
    public void updateUser(Long id, UserDto updatedUser) {
        Users u = userRepository.findById(id).get();
        u.update(updatedUser);

        userRepository.save(u);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUser(Long id) {
        Users u = userRepository.findById(id).get();

        return u.toDto();
    }

    @Override
    public UserDto getLoggedUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        String authority = authorities.get(0).getAuthority();

        Users user = new Users();

        if(Objects.equals(authority, "ROLE_ADMIN")) {
            Role role = new Role();
            role.setRole(authority);

            user.setUserName(userName);
            user.setRole(role);
        } else {
            user = userRepository.findByUserName(userName);
        }

        return user.toDto();
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
    public boolean existsUserWithId(Long id) {
        return userRepository.findById(id).isPresent();
    }
}

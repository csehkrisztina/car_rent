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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(UserDto userDto) {

        if(roleRepository.findAll().isEmpty())
            createRoles();

        Users user = new Users();
        user.update(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(1);
        user.setRole(new HashSet<Role>(Arrays.asList(getRoleForUser())));

        userRepository.save(user);
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

    public void createRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setRole("ADMIN");

        Role roleUser = new Role();
        roleUser.setRole("USER");

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
    }

    public Role getRoleForUser() {
        if(userRepository.findAll().isEmpty())
            return roleRepository.findByRole("ADMIN");
        return roleRepository.findByRole("USER");
    }
}

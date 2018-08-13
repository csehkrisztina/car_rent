package com.rent.service_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Role;
import com.rent.model.entity.Users;
import com.rent.model.repository.RoleRepository;
import com.rent.model.repository.UserRepository;
import com.rent.service_api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        user.setActive(true);
        user.setRole(new HashSet<Role>(Arrays.asList(getRoleForUser())));

        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDto updatedUser) {
        Users u = userRepository.findById(id).get();
        u.update(updatedUser);

        if(updatedUser.getPassword() != "") {
            u.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

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

    @Override
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
    }

    @Override
    public boolean isLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (auth.getPrincipal() == null) ? false : true;
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

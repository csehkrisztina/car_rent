package com.rent.service_api;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Users findUserByEmail(String email);

    void saveUser(UserDto user);

    void updateUser(Long id, UserDto userToUpdate);

    void deleteUser(String email);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    boolean existsUserWithId(Long id);

    boolean isAdmin();

    boolean isLoggedInUser();
}

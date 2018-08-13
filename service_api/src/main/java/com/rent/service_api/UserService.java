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

    void deleteUser(Long id);

    UserDto getUser(Long id);

//    UserDto getLoggedUser();

    List<UserDto> getAllUsers();

    boolean existsUserWithId(Long id);
}

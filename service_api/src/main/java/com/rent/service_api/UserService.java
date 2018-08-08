package com.rent.service_api;

import com.rent.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void saveUser(UserDto user);

    void updateUser(Long id, UserDto userToUpdate);

    void deleteUser(Long id);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    boolean existsUserWithId(Long id);
}

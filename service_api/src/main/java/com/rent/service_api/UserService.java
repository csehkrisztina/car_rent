package com.rent.service_api;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void addUser(UserDto user);

    void updateUser(Long id, UserDto userToUpdate);

    void deleteUser(Long id);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();
}

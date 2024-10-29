package com.userservice.service;

import com.userservice.dto.UserDto;
import com.userservice.enums.RoleEnum;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> getUserByEmail(String email);

    ResponseEntity<Object> getUserByUsername(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    ResponseEntity<Object> registerUser(UserDto userDto);

}

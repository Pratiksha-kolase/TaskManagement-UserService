package com.userservice.controller;

import com.userservice.dto.UserDto;
import com.userservice.enums.RoleEnum;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-user-by-email")
    public ResponseEntity<Object> getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }
    @GetMapping("/get-user-by-username")
    public ResponseEntity<Object> getUserByUsername(@RequestParam String userName){
        return userService.getUserByUsername(userName);
    }

    @GetMapping("/exists-by-username")
    public Boolean existsByUserName(@RequestParam String userName){
        return userService.existsByUserName(userName);
    }
    @GetMapping("/exists-by-email")
    public Boolean existsByEmail(@RequestParam String email){
        return userService.existsByEmail(email);
    }

    @PostMapping("/register-user")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }
}

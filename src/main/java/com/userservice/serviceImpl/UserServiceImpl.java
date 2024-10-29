package com.userservice.serviceImpl;

import com.userservice.dto.UserDto;
import com.userservice.enums.RoleEnum;
import com.userservice.model.RoleModel;
import com.userservice.model.UserModel;
import com.userservice.repository.RoleRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public ResponseEntity<Object> getUserByEmail(String email) {
        try {
            Optional<UserModel> user = userRepository.findByEmailAndRoles_Name(email, RoleEnum.ROLE_USER);
            if (!user.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(user.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Object> getUserByUsername(String userName) {
        try {
            Optional<UserModel> user = userRepository.findByUsernameAndRoles_Name(userName, RoleEnum.ROLE_USER);
            if (!user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok(user.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean existsByUserName(String userName) {
        try {
            boolean existsByUserName = userRepository.existsByUsername(userName);
            return existsByUserName;
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Boolean existsByEmail(String email) {
        try {
            boolean existsByEmail = userRepository.existsByEmail(email);
            return existsByEmail;
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Object> registerUser(UserDto userDto) {
        UserModel user = new UserModel(userDto.getUsername(),
                userDto.getEmail(),
                encoder.encode(userDto.getPassword()));

        Set<RoleModel> strRoles = userDto.getRoles();
        Set<RoleModel> roles = new HashSet<>();


        if (strRoles == null) {
            RoleModel userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    RoleModel adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                    roles.add(adminRole);
                } else {
                    RoleModel userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

}

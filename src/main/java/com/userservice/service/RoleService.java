package com.userservice.service;

import com.userservice.enums.RoleEnum;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<Object> getRoleByName(RoleEnum name);
}

package com.userservice.controller;

import com.userservice.enums.RoleEnum;
import com.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/get-role-by-name")
    public ResponseEntity<Object> getRoleByName(@RequestParam RoleEnum name ){
        return roleService.getRoleByName(name);
    }
}

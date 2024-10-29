package com.userservice.dto;

import com.userservice.enums.RoleEnum;

public class RoleDto {
    private Long roleId;


    private RoleEnum name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}

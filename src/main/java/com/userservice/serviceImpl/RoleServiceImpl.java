package com.userservice.serviceImpl;

import com.userservice.enums.RoleEnum;
import com.userservice.model.RoleModel;
import com.userservice.repository.RoleRepository;
import com.userservice.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<Object> getRoleByName(RoleEnum name) {
        try {
            Optional<RoleModel> role = roleRepository.findByName(name);
            if (!role.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(role.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

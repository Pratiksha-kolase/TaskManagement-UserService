package com.userservice.repository;

import com.userservice.enums.RoleEnum;
import com.userservice.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<RoleModel,Long> {
    Optional<RoleModel> findByName(RoleEnum name);
}

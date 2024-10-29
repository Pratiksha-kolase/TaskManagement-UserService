package com.userservice.repository;

import com.userservice.enums.RoleEnum;
import com.userservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Query("select (count(u) > 0) from UserModel u where u.email = ?1")
    boolean existsByEmail(String email);

    @Query("select (count(u) > 0) from UserModel u where u.username = ?1")
    boolean existsByUsername(String username);

    Optional<UserModel> findByEmailAndRoles_Name(String email, RoleEnum name);

    Optional<UserModel> findByUsernameAndRoles_Name(String username, RoleEnum name);
}

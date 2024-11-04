package com.example.vuvantrung.repository.user;

import com.example.vuvantrung.entity.Role;
import com.example.vuvantrung.entity.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
//    Optional<User> findByEmail(String email);
    List<User> findAllUserWithRole(String role);
}

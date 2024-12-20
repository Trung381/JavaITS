package com.example.vuvantrung.repository.user;

import com.example.vuvantrung.entity.User;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//, UserRepositoryCustom
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom{
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username); // New search by username
    @NotNull
    Optional<User> findById(@NotNull Integer id);
}

package com.example.vuvantrung.service;

import com.example.vuvantrung.DTO.AuthenticationRequest;
import com.example.vuvantrung.DTO.AuthenticationResponse;
import com.example.vuvantrung.DTO.RegisterRequest;
import com.example.vuvantrung.entity.BlacklistedToken;
import com.example.vuvantrung.repository.BlacklistedTokenRepository;
import com.example.vuvantrung.repository.RoleRepository;
import com.example.vuvantrung.repository.UserRepository;
import com.example.vuvantrung.entity.Role;
import com.example.vuvantrung.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 RoleRepository roleRepository,
                                 JwtService jwtService,
                                 BlacklistedTokenRepository blacklistedTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }

    public User register(RegisterRequest request) {
        Role role = roleRepository.findByName("USER");
        final var user = new User(
                request.firstname(),
                request.lastname(),
                request.email(),
                request.username(),
                request.address(),
                passwordEncoder.encode(request.password()),
                role
        );
        userRepository.save(user);
        log.info("User has username {} is registered successfully", request.username());
        return user;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        final String token = jwtService.generateToken(user);
        log.info("{} login successfully", request.username());
        return AuthenticationResponse.fromUser(
                token,
                user,  // Pass user object directly
                "Authentication successful",
                200
        );
    }


    public void logout(String token) {
        BlacklistedToken blacklistedToken = new BlacklistedToken(token, LocalDateTime.now());
        blacklistedTokenRepository.save(blacklistedToken);
    }

    public String extractToken(HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        return header.substring(7);
    }

}

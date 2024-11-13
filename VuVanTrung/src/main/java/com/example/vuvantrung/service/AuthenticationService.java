package com.example.vuvantrung.service;

import com.example.vuvantrung.dto.request.AuthenticationRequest;
import com.example.vuvantrung.dto.response.AuthenticationResponse;
import com.example.vuvantrung.dto.request.ChangePasswordRequest;
import com.example.vuvantrung.dto.request.RegisterRequest;
import com.example.vuvantrung.entity.BlacklistedToken;
import com.example.vuvantrung.repository.blacklistedToken.BlacklistedTokenRepository;
import com.example.vuvantrung.repository.role.RoleRepository;
import com.example.vuvantrung.repository.user.UserRepository;
import com.example.vuvantrung.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//@Slf4j
//@Service
//public class AuthenticationService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final RoleRepository roleRepository;
//    private final JwtService jwtService;
//    private final BlacklistedTokenRepository blacklistedTokenRepository;
//
//    public AuthenticationService(UserRepository userRepository,
//                                 PasswordEncoder passwordEncoder,
//                                 AuthenticationManager authenticationManager,
//                                 RoleRepository roleRepository,
//                                 JwtService jwtService,
//                                 BlacklistedTokenRepository blacklistedTokenRepository) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.roleRepository = roleRepository;
//        this.jwtService = jwtService;
//        this.blacklistedTokenRepository = blacklistedTokenRepository;
//    }
//
//    public User register(RegisterRequest request) {
//        Role role = roleRepository.findByName("USER");
//        final var user = new User(
//                request.firstname(),
//                request.lastname(),
//                request.email(),
//                request.username(),
//                request.address(),
//                passwordEncoder.encode(request.password()),
//                role
//        );
//        userRepository.save(user);
//        log.info("User has username {} is registered successfully", request.username());
//        return user;
//    }
//
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.username(), request.password())
//        );
//
//        var user = userRepository.findByUsername(request.username())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        final String token = jwtService.generateToken(user);
//        log.info("{} login successfully", request.username());
//        return AuthenticationResponse.fromUser(
//                token,
//                user,  // Pass user object directly
//                "Authentication successful",
//                200
//        );
//    }
//
//
//    public void logout(String token) {
//        BlacklistedToken blacklistedToken = new BlacklistedToken(token, LocalDateTime.now());
//        blacklistedTokenRepository.save(blacklistedToken);
//    }
//
//    public String extractToken(HttpServletRequest request) {
//        final String header = request.getHeader("Authorization");
//        if (header == null || !header.startsWith("Bearer ")) {
//            throw new RuntimeException("Invalid token");
//        }
//        return header.substring(7);
//    }
//
//    public String changePassword(ChangePasswordRequest request) {
//        // Sử dụng trực tiếp các trường của `request` như `request.username()`, `request.currentPassword()`, và `request.newPassword()`
//        User user = userRepository.findByUsername(request.username())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
//            throw new RuntimeException("Current password is incorrect");
//        }
//
//        user.setPassword(passwordEncoder.encode(request.newPassword()));
//        userRepository.save(user);
//
//        return "Password changed successfully";
//    }
//
//}
import com.example.vuvantrung.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    public ResponseEntity<BaseResponse<?>> register(RegisterRequest request) {
        try {
            User user = new User(
                    request.firstname(),
                    request.lastname(),
                    request.email(),
                    request.username(),
                    request.address(),
                    passwordEncoder.encode(request.password()),
                    roleRepository.findByName("USER")
            );
            userRepository.save(user);
            log.info("User with username {} registered successfully", request.username());
            return ResponseEntity.ok(BaseResponse.success(user, "User registered successfully."));
        } catch (Exception e) {
            log.error("Error during registration: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.error("Registration failed", e.getMessage()));
        }
    }

    public ResponseEntity<BaseResponse<?>> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            User user = userRepository.findByUsername(request.username())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String token = jwtService.generateToken(user);
            log.info("{} login successfully", request.username());

            AuthenticationResponse authResponse = AuthenticationResponse.fromUser(
                    token,
                    user
            );


            return ResponseEntity.ok(BaseResponse.success(authResponse, "Authentication successful."));
        } catch (Exception e) {
            log.error("Authentication error: ", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponse.error("Authentication failed", e.getMessage()));
        }
    }

    public ResponseEntity<BaseResponse<?>> logout(HttpServletRequest request) {
        try {
            String token = extractToken(request);
            BlacklistedToken blacklistedToken = new BlacklistedToken(token, LocalDateTime.now());
            blacklistedTokenRepository.save(blacklistedToken);
            log.info("Token invalidated successfully");

            return ResponseEntity.ok(BaseResponse.success("Token invalidated successfully"));
        } catch (Exception e) {
            log.error("Logout error: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error("Logout failed", e.getMessage()));
        }
    }

    public ResponseEntity<BaseResponse<?>> changePassword(ChangePasswordRequest request) {
        try {
            User user = userRepository.findByUsername(request.username())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
                throw new RuntimeException("Current password is incorrect");
            }

            user.setPassword(passwordEncoder.encode(request.newPassword()));
            userRepository.save(user);
            log.info("Password changed successfully for user {}", request.username());

            return ResponseEntity.ok(BaseResponse.success("Password changed successfully"));
        } catch (Exception e) {
            log.error("Change password error: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error("Change password failed", e.getMessage()));
        }
    }

    private String extractToken(HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        return header.substring(7);
    }
}


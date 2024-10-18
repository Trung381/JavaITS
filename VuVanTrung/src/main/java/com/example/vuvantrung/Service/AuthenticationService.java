package com.example.vuvantrung.Service;

import com.example.vuvantrung.DTO.AuthenticationRequest;
import com.example.vuvantrung.DTO.AuthenticationResponse;
import com.example.vuvantrung.DTO.RegisterRequest;
import com.example.vuvantrung.Entity.BlacklistedToken;
import com.example.vuvantrung.Repository.BlacklistedTokenRepository;
import com.example.vuvantrung.Repository.RoleRepository;
import com.example.vuvantrung.Repository.UserRepository;
import com.example.vuvantrung.Entity.Role;
import com.example.vuvantrung.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                                 BlacklistedTokenRepository blacklistedTokenRepository) { // Thêm vào constructor
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }

    public User register(RegisterRequest request) {
        Role role = roleRepository.findByName("USER"); // Lấy ra role có name = USER từ db
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
        return user;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        final String token = jwtService.generateToken(user);
//        return new AuthenticationResponse(token);
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

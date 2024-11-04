package com.example.vuvantrung.controller;

import com.example.vuvantrung.DTO.AuthenticationRequest;
import com.example.vuvantrung.DTO.AuthenticationResponse;
import com.example.vuvantrung.DTO.ChangePasswordRequest;
import com.example.vuvantrung.DTO.RegisterRequest;
import com.example.vuvantrung.repository.BlacklistedTokenRepository;
import com.example.vuvantrung.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    public AuthController(AuthenticationService authenticationService, BlacklistedTokenRepository blacklistedTokenRepository) {
        this.authenticationService = authenticationService;
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        final String header = request.getHeader("Authorization");
//        if (header == null || !header.startsWith("Bearer ")) {
//            return ResponseEntity.badRequest().body("Invalid token");
//        }
//        final String token = header.substring(7);
//
//        BlacklistedToken blacklistedToken = new BlacklistedToken(token, LocalDateTime.now());
//        blacklistedTokenRepository.save(blacklistedToken);
//
//        return ResponseEntity.ok("Logged out successfully, token invalidated");
//    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        try {
            String token = authenticationService.extractToken(request);
            authenticationService.logout(token);
            return ResponseEntity.ok("Logged out successfully, token invalidated");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        String result = authenticationService.changePassword(request);
        return ResponseEntity.ok(result);
    }
}


package com.example.vuvantrung.controller;

import com.example.vuvantrung.dto.*;
import com.example.vuvantrung.dto.request.AuthenticationRequest;
import com.example.vuvantrung.dto.request.ChangePasswordRequest;
import com.example.vuvantrung.dto.request.RegisterRequest;
import com.example.vuvantrung.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//
//    private final AuthenticationService authenticationService;
//    private final BlacklistedTokenRepository blacklistedTokenRepository;
//
//    public AuthController(AuthenticationService authenticationService, BlacklistedTokenRepository blacklistedTokenRepository) {
//        this.authenticationService = authenticationService;
//        this.blacklistedTokenRepository = blacklistedTokenRepository;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<BaseResponse<User>> register(@Valid @RequestBody RegisterRequest request) {
//        User registeredUser = authenticationService.register(request);
//        return ResponseEntity.ok(BaseResponse.success(registeredUser, "User registered successfully."));
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<BaseResponse<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest request) {
//        AuthenticationResponse authResponse = authenticationService.authenticate(request);
//        return ResponseEntity.ok(BaseResponse.success(authResponse, "Authentication successful."));
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<BaseResponse<String>> logout(HttpServletRequest request) {
//        try {
//            String token = authenticationService.extractToken(request);
//            authenticationService.logout(token);
//            return ResponseEntity.ok(BaseResponse.success("Logged out successfully, token invalidated"));
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(BaseResponse.error("Invalid token", e.getMessage()));
//        }
//    }
//
//    @PostMapping("/change-password")
//    public ResponseEntity<BaseResponse<String>> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
//        String result = authenticationService.changePassword(request);
//        return ResponseEntity.ok(BaseResponse.success(result, "Password changed successfully."));
//    }
//}
//

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<BaseResponse<?>> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<?>> logout(HttpServletRequest request) {
        return authenticationService.logout(request);
    }

    @PostMapping("/change-password")
    public ResponseEntity<BaseResponse<?>> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        return authenticationService.changePassword(request);
    }
}
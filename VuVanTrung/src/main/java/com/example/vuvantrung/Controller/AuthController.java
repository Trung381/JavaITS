package com.example.vuvantrung.Controller;

import com.example.vuvantrung.DTO.AuthenticationRequest;
import com.example.vuvantrung.DTO.AuthenticationResponse;
import com.example.vuvantrung.DTO.RegisterRequest;
import com.example.vuvantrung.Entity.BlacklistedToken;
import com.example.vuvantrung.Entity.User;
import com.example.vuvantrung.Repository.BlacklistedTokenRepository;
import com.example.vuvantrung.Service.AuthenticationService;
import com.example.vuvantrung.Service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//
//    private JwtService jwtService;
//
//    private final AuthenticationService authenticationService;
//
//    // thêm để làm invalidate token
//    private final BlacklistedTokenRepository blacklistedTokenRepository;
//
//    public AuthController(AuthenticationService authenticationService, BlacklistedTokenRepository blacklistedTokenRepository) {
//        this.authenticationService = authenticationService;
//        // thêm để invalidate token
//        this.blacklistedTokenRepository = blacklistedTokenRepository;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
////    @PostMapping("/register")
////    public String register(@RequestBody RegisterRequest request) {
////        authenticationService.register(request);
////        return "haizzz";
////    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//    }
//
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
//}

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    public AuthController(AuthenticationService authenticationService, BlacklistedTokenRepository blacklistedTokenRepository) {
        this.authenticationService = authenticationService;
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        final String token = header.substring(7);

        BlacklistedToken blacklistedToken = new BlacklistedToken(token, LocalDateTime.now());
        blacklistedTokenRepository.save(blacklistedToken);

        return ResponseEntity.ok("Logged out successfully, token invalidated");
    }
}


package com.example.vuvantrung.Service;

import com.example.vuvantrung.DTO.AuthenticationRequest;
import com.example.vuvantrung.DTO.AuthenticationResponse;
import com.example.vuvantrung.DTO.RegisterRequest;
import com.example.vuvantrung.Repository.RoleRepository;
import com.example.vuvantrung.Repository.UserRepository;
import com.example.vuvantrung.Entity.Role;
import com.example.vuvantrung.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//public class AuthenticationService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final RoleRepository roleRepository;
//
//    public AuthenticationService(UserRepository userRepository,
//                                 PasswordEncoder passwordEncoder,
//                                 AuthenticationManager authenticationManager, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.roleRepository = roleRepository;
//    }
//
////    public AuthenticationResponse register(RegisterRequest request) {
//    public void register(RegisterRequest request) {
//        Role role = roleRepository.findByName("USER");//lay ra role co name = USER tu db
//        final var user = new User(
//                request.firstname(),
//                request.lastname(),
//                request.email(),
//                request.username(),
//                request.address(),  // Modify if address needs to be handled
//                passwordEncoder.encode(request.password()),
////                new Role("USER")
//                role
//        );
//        System.out.println("hmm");
//        userRepository.save(user);
//        System.out.println("123");
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.username(), request.password())
//        );
//        var user = userRepository.findByUsername(request.username())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        final String token = JwtService.generateToken(user);
//        return new AuthenticationResponse(token);
//    }
//}

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtService jwtService; // Thêm JwtService vào đây

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 RoleRepository roleRepository,
                                 JwtService jwtService) { // Thêm vào constructor
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService; // Gán jwtService
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
        return new AuthenticationResponse(token);
    }
}

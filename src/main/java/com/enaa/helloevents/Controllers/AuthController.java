package com.enaa.helloevents.Controllers;

import com.enaa.helloevents.Repositories.UserRepositorie;
import com.enaa.helloevents.configuration.AuthenticationRequest;
import com.enaa.helloevents.configuration.AuthenticationResponse;
import com.enaa.helloevents.configuration.JwtUtils;
import com.enaa.helloevents.Entities.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepositorie userRepositorie;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepositorie userRepositorie,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils,
                          AuthenticationManager authenticationManager) {
        this.userRepositorie = userRepositorie;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepositorie.findUserByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("USER");

        User savedUser = userRepositorie.save(user);

        return ResponseEntity.ok(savedUser);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();


        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            if (authentication.isAuthenticated()) {
//                String token = jwtUtils.generateToken(username);
//
//                Map<String, Object> authData = new HashMap<>();
//                authData.put("token", token);
//                authData.put("type", "Bearer");
//
//                return ResponseEntity.ok(authData);
//            }
//
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }


        return ResponseEntity.ok(authenticate(request));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
        );

        User user = userRepositorie.findUserByUsername(authenticationRequest.getUsername()).orElseThrow(()->new RuntimeException("user not found !!!!"));
        return getAuthenticationResponse(user);
    }


    private AuthenticationResponse getAuthenticationResponse(User user) {

       String authorities = user.getAuthorities().stream()
               .map(GrantedAuthority::getAuthority)
               .collect(Collectors.joining(","));

        Map<String,String> userAuthorities = new HashMap<>();

        userAuthorities.put("authorities",authorities);

        var jwtToken = jwtUtils.generateToken(userAuthorities,user.getUsername());
        return AuthenticationResponse.builder().jwtToken(jwtToken).build();
    }
}
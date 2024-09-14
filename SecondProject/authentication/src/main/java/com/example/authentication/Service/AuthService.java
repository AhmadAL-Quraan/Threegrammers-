package com.example.authentication.Service;

import java.util.HashMap;
import java.util.List;


import com.example.authentication.Security.JWTUtility;
import com.example.authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserService userService,  JWTUtility jwtUtility, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }

    public List<String> signup(String username, String email, String password) {
        // Encryption
        String encodedPassword = passwordEncoder.encode(password);
        User user = userService.createUser(encodedPassword, email, username);
        return List.of(username, email);
    }

    public ResponseEntity<?> login(String email, String password) {
        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email shouldn't be empty.");
        }
        if (password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password shouldn't be null.");
        }
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Retrieve the authenticated user's details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generate the access and refresh tokens
            String accessToken = jwtUtility.generateAccessToken(userDetails.getUsername());
            String refreshToken = jwtUtility.generateRefreshToken(userDetails.getUsername());

            // Prepare the response body
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);

            // Return the tokens in the response
            return ResponseEntity.ok(tokens);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }
    }
     
}

package com.example.tms.controller;

import com.example.tms.dto.AuthRequest;
import com.example.tms.dto.AuthResponse;
import com.example.tms.services.CustomUserDetailsService;
import com.example.tms.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtUtil jwtUtil;

    public AuthController(CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil)
    {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request)
    {
        try{
            // 1. Authenticate username & password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            // 2. If authentication succeeds, load user details
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
            // 3. Generate JWT token
            String token = jwtUtil.generateToken(userDetails);
            // 4. Return token in response
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(new AuthResponse("Invalid username or password"));
        }

    }
}

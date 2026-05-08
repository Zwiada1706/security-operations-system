package com.securityops.backend.auth.controller;

import com.securityops.backend.auth.dto.LoginRequest;
import com.securityops.backend.auth.dto.LoginResponse;
import com.securityops.backend.auth.service.AuthService;
import com.securityops.backend.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }
}
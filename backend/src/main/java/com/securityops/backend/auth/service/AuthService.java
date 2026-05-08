package com.securityops.backend.auth.service;

import com.securityops.backend.auth.dto.LoginRequest;
import com.securityops.backend.auth.dto.LoginResponse;
import com.securityops.backend.user.entity.User;
import com.securityops.backend.user.repository.UserRepository;
import com.securityops.backend.auth.dto.RegisterRequest;
import com.securityops.backend.role.entity.Role;
import com.securityops.backend.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String fakeToken = "TEMP_TOKEN_" + user.getId();

        return new LoginResponse(
                fakeToken,
                user.getEmail(),
                user.getRole().getName()
        );
    }

    public void register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // Get default role
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        // Create user
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .isActive(true)
                .role(role)
                .build();

        userRepository.save(user);
    }
}
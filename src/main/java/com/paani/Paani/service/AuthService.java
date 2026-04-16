package com.paani.Paani.service;


import com.paani.Paani.dto.AuthRequest;
import com.paani.Paani.dto.AuthResponse;
import com.paani.Paani.dto.RegisterRequest;
import com.paani.Paani.model.Role;
import com.paani.Paani.model.User;
import com.paani.Paani.repository.UserRepository;
import com.paani.Paani.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtProvider jwtProvider;


    public void register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) throw new RuntimeException("Username already taken");
        User user = User.builder().username(req.getUsername()).email(req.getEmail()).password(passwordEncoder.encode(req.getPassword())).role(req.getRole() != null ? req.getRole() : Role.CUSTOMER).build();
        userRepository.save(user);
    }


    public AuthResponse login(AuthRequest req) {
        User user = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) throw new RuntimeException("Invalid credentials");
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }


    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
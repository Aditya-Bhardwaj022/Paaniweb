package com.paani.Paani.controller;


import com.paani.Paani.dto.AuthRequest;
import com.paani.Paani.dto.AuthResponse;
import com.paani.Paani.dto.RegisterRequest;
import com.paani.Paani.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        String username = authentication.getName();
        authService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }
}
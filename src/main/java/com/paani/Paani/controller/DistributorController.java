package com.paani.Paani.controller;


import com.paani.Paani.model.User;
import com.paani.Paani.repository.UserRepository;
import com.paani.Paani.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/distributor")
public class DistributorController {
    @Autowired private DistributorService distributorService;
    @Autowired private UserRepository userRepository;


    @GetMapping("/me")
    public ResponseEntity<?> myProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user.getDistributor());
    }

    @PostMapping("/approve/{distributorId}")
    public ResponseEntity<?> approve(@PathVariable Long distributorId) {
        return ResponseEntity.ok(distributorService.approve(distributorId));
    }

    @PostMapping("/reject/{distributorId}")
    public ResponseEntity<?> reject(@PathVariable Long distributorId) {
        return ResponseEntity.ok(distributorService.reject(distributorId));
    }
}
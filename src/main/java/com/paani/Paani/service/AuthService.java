package com.paani.Paani.service;


import com.paani.Paani.dto.AuthRequest;
import com.paani.Paani.dto.AuthResponse;
import com.paani.Paani.dto.RegisterRequest;
import com.paani.Paani.model.*;
import com.paani.Paani.repository.CustomerRepository;
import com.paani.Paani.repository.DistributorRepository;
import com.paani.Paani.repository.UserRepository;
import com.paani.Paani.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;


@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private DistributorRepository distributorRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtProvider jwtProvider;


    public void register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) throw new RuntimeException("Username already taken");

        Role role = req.getRole() != null ? req.getRole() : Role.CUSTOMER;
        User user = User.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);

        // Create the corresponding profile based on role
        if (role == Role.CUSTOMER) {
            Customer customer = Customer.builder()
                    .fullName(req.getFullName())
                    .address(req.getAddress())
                    .phoneNumber(req.getPhoneNumber())
                    .email(req.getEmail())
                    .subscriptionType(req.getSubscriptionType())
                    .user(user)
                    .build();

            if (req.getSubscriptionType() != null && !req.getSubscriptionType().isBlank()) {
                customer.setSubscriptionStartDate(LocalDate.now());
                if ("MONTHLY".equalsIgnoreCase(req.getSubscriptionType())) customer.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
                else if ("WEEKLY".equalsIgnoreCase(req.getSubscriptionType())) customer.setSubscriptionEndDate(LocalDate.now().plusWeeks(1));
                else if ("DAILY".equalsIgnoreCase(req.getSubscriptionType())) customer.setSubscriptionEndDate(LocalDate.now().plusDays(1));
            }

            customerRepository.save(customer);
        } else if (role == Role.DISTRIBUTOR) {
            Distributor distributor = Distributor.builder()
                    .distributorName(req.getDistributorName())
                    .licenseNumber(req.getLicenseNumber())
                    .serviceArea(req.getServiceArea())
                    .capacityPerDay(req.getCapacityPerDay())
                    .contactNumber(req.getContactNumber())
                    .approvalStatus(ApprovalStatus.PENDING)
                    .trialStartTimestamp(Instant.now().toEpochMilli())
                    .user(user)
                    .build();

            distributorRepository.save(distributor);
        }
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
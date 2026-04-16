package com.paani.Paani.dto;

import com.paani.Paani.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Role role; // CUSTOMER, DISTRIBUTOR

    // Customer profile fields (required when role = CUSTOMER)
    private String fullName;
    private String address;
    private String phoneNumber;
    private String subscriptionType; // DAILY, WEEKLY, MONTHLY

    // Distributor profile fields (required when role = DISTRIBUTOR)
    private String distributorName;
    private String licenseNumber;
    private String serviceArea;
    private Integer capacityPerDay;
    private String contactNumber;
}

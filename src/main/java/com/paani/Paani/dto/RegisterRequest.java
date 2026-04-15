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

    private Role role; // Optional: e.g., ADMIN, CUSTOMER, DISTRIBUTOR
}

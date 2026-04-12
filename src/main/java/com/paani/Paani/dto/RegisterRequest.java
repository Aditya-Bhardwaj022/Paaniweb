package com.paani.Paani.dto;

import com.paani.Paani.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Role role; // Optional: e.g., ADMIN, CUSTOMER, DISTRIBUTOR
}

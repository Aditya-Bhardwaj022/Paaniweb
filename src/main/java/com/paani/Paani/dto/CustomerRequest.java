package com.paani.Paani.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CustomerRequest {
    @NotBlank private String fullName;
    @NotBlank private String address;
    @NotBlank @Size(min = 10, max = 15) private String phoneNumber;
    @Email private String email;
    private String subscriptionType; // DAILY, WEEKLY, MONTHLY or NONE
}
package com.paani.Paani.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PaymentRequest {
    @NotNull private Long orderId;
    @NotNull private Double amount;
    @NotBlank private String  gateway; // MOCK, RAZORPAY, STRIPE
}
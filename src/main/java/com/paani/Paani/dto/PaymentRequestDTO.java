package com.paani.Paani.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long orderId;
    private Long userId;
    private double amount;
    private String paymentMethod; // "UPI", "CREDIT_CARD", etc.
}

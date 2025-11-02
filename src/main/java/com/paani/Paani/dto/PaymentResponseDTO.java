package com.paani.Paani.dto;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long id;
    private Long orderId;
    private double amount;
    private String paymentMethod;
    private String status;
    private String transactionId;
}

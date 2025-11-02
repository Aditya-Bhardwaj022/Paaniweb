package com.paani.Paani.dto;

import lombok.Data;

@Data
public class OrderResponseDTO {

    private Long id;
    private Long customerId;
    private Long distributorId;
    private String customerName;
    private String distributorName;
    private int quantity;
    private String deliveryDate;
    private String deliverySlot;
    private double totalAmount;
    private String status;
}

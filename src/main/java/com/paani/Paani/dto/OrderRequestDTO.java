package com.paani.Paani.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private Long customerId;       // 👈 Needed for getCustomerId()
    private Long distributorId;
    private int quantity;
    private String deliveryDate;   // e.g. "2025-10-29"
    private String deliveryTime;   // e.g. "8 AM"
    private double totalAmount;
}

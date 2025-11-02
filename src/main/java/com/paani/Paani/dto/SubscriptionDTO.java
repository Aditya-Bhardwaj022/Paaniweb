package com.paani.Paani.dto;

import lombok.Data;

@Data
public class SubscriptionDTO {
    private Long distributorId;
    private String startDate;
    private String endDate;
    private double amount;
    private String status;
}

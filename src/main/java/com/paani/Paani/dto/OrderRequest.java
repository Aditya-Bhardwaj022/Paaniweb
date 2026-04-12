package com.paani.Paani.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class OrderRequest {
    @NotNull @Min(1) private Double quantity; // liters
    @NotBlank private String deliverySlot; // MORNING or EVENING
    @NotNull @Min(0) private Double amount; // amount before platform fee
}
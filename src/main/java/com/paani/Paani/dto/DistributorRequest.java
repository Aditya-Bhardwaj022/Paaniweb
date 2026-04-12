package com.paani.Paani.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DistributorRequest {
    @NotBlank private String distributorName;
    @NotBlank private String licenseNumber;
    @NotBlank private String serviceArea;
    @NotNull private Integer capacityPerDay;
    @NotBlank private String contactNumber;
}
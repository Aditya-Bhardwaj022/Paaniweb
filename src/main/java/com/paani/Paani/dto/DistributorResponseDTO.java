package com.paani.Paani.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistributorResponseDTO {
    private Long id;
    private String name;
    private String companyName;
    private String phone;
    private String address;
    private String approvalStatus;
}

package com.paani.Paani.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistributorRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String companyName;
    private String address;
}

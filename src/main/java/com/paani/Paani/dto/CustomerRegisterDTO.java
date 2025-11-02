package com.paani.Paani.dto;

import lombok.Data;

@Data
public class CustomerRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
}

package com.paani.Paani.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
}

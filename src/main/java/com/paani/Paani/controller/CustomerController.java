package com.paani.Paani.controller;

import com.paani.Paani.dto.CustomerRegisterDTO;
import com.paani.Paani.dto.CustomerResponseDTO;
import com.paani.Paani.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegisterDTO dto) {
        return ResponseEntity.ok(customerService.registerCustomer(dto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}

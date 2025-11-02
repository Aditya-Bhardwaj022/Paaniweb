package com.paani.Paani.controller;

import com.paani.Paani.dto.PaymentRequestDTO;
import com.paani.Paani.dto.PaymentResponseDTO;
import com.paani.Paani.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(paymentService.processPayment(dto));
    }
}

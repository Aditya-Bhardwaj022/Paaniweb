package com.paani.Paani.controller;


import com.paani.Paani.dto.PaymentRequest;
import com.paani.Paani.dto.PaymentResponse;
import com.paani.Paani.model.Payment;
import com.paani.Paani.model.PaymentStatus;
import com.paani.Paani.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired private PaymentService paymentService;


    // manual initiation (will attempt to call gateway bean if available)
    @PostMapping("/initiate")
    public ResponseEntity<?> initiate(@Valid @RequestBody PaymentRequest req) {
        Payment p = paymentService.initiatePayment(req.getOrderId(), req.getAmount(), req.getGateway());
        return ResponseEntity.ok(new PaymentResponse(p.getId(), p.getStatus().name()));
    }


    // manual confirm
    @PostMapping("/confirm/{paymentId}")
    public ResponseEntity<?> confirm(@PathVariable Long paymentId, @RequestParam("status") String status) {
        Payment p = paymentService.confirmPayment(paymentId, PaymentStatus.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(new PaymentResponse(p.getId(), p.getStatus().name()));
    }
}
package com.paani.Paani.service;

import com.paani.Paani.dto.*;
import com.paani.Paani.entity.*;
import com.paani.Paani.entity.enums.Status;
import com.paani.Paani.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(String.valueOf(Status.ACTIVE));
        payment.setTransactionId(UUID.randomUUID().toString());
        paymentRepository.save(payment);

        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setId(payment.getId());
        res.setOrderId(order.getId());
        res.setAmount(payment.getAmount());
        res.setPaymentMethod(payment.getPaymentMethod());
        res.setStatus(payment.getStatus().toString());
        res.setTransactionId(payment.getTransactionId());

        return res;
    }
}

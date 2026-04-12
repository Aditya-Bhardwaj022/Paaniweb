package com.paani.Paani.service;


import com.paani.Paani.model.Payment;
import com.paani.Paani.model.PaymentStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@Service("MOCK")
public class MockPaymentGateway implements PaymentGateway {
    @Override
    public Payment initiatePayment(Payment payment) {
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setExternalPaymentId("MOCK_" + System.currentTimeMillis());
        payment.setCreatedAt(LocalDateTime.now());
        return payment;
    }
}
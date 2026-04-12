package com.paani.Paani.service;
import com.paani.Paani.model.OrderEntity;
import com.paani.Paani.model.Payment;
import com.paani.Paani.model.PaymentStatus;
import com.paani.Paani.repository.OrderRepository;
import com.paani.Paani.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@Service
public class PaymentService {
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ApplicationContext applicationContext; // to get gateway beans by name


    // initiate: creates a payment (INITIATED) and if gateway is MOCK, auto mark success
    public Payment initiatePayment(Long orderId, Double amount, String gatewayName) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Payment p = Payment.builder().order(order).amount(amount).gateway(gatewayName).status(PaymentStatus.INITIATED).createdAt(LocalDateTime.now()).build();
        p = paymentRepository.save(p);


// If a gateway bean exists with that name, call it (e.g., MOCK). Otherwise leave as initiated
        try {
            PaymentGateway gw = applicationContext.getBean(gatewayName, PaymentGateway.class);
            Payment updated = gw.initiatePayment(p);
            return paymentRepository.save(updated);
        } catch (Exception ex) {
// no gateway bean found - return initiated payment
            return p;
        }
    }


    // manual confirm endpoint
    public Payment confirmPayment(Long paymentId, PaymentStatus status) {
        Payment p = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        p.setStatus(status);
        return paymentRepository.save(p);
    }
}
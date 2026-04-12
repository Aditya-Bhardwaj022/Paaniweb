package com.paani.Paani.service;


import com.paani.Paani.model.Payment;


public interface PaymentGateway {
    Payment initiatePayment(Payment payment);
}
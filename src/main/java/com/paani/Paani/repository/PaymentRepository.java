package com.paani.Paani.repository;


import com.paani.Paani.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
package com.paani.Paani.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String gateway; // e.g., MOCK, RAZORPAY, STRIPE


    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    private Double amount;


    private String currency = "INR";


    private String externalPaymentId; // id returned by gateway


    private LocalDateTime createdAt;


    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
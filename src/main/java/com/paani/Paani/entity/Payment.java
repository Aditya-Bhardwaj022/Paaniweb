package com.paani.Paani.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the order
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Amount paid for the order
    private Double amount;

    // Add this field for payment method (UPI, CARD, CASH, etc.)
    private String paymentMethod;

    // Payment status (SUCCESS, FAILED, PENDING, etc.)
    private String status;

    //  Transaction ID
    private String transactionId;

    // Timestamp
    private LocalDateTime paymentDate = LocalDateTime.now();
}

package com.paani.Paani.entity;

import com.paani.Paani.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer placing the order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Distributor fulfilling the order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    // Order details
    private LocalDate deliveryDate;

    private int quantity;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Optional fields
    private String deliveryTime;
}

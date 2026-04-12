package com.paani.Paani.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;


    private Double quantity; // liters
    private String deliverySlot; // MORNING or EVENING
    private LocalDateTime requestedAt;


    private String status; // PENDING, IN_PROGRESS, DELIVERED


    private Double amount; // charge before platform fee
}
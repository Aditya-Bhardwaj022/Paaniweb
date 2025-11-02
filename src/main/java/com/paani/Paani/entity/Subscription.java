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
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each subscription belongs to one distributor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    private LocalDate startDate;
    private LocalDate endDate;

    // monthly fee, e.g., ₹300
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}

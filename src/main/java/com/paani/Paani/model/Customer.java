package com.paani.Paani.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;


    private String subscriptionType; // DAILY, WEEKLY, MONTHLY or NONE
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
package com.paani.Paani.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "distributors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String distributorName;
    private String licenseNumber;
    private String serviceArea;
    private int capacityPerDay;
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    // ✅ Links distributor to the User account
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // ✅ Marks when 3-month free trial begins
    private Long trialStartTimestamp;

    // ✅ For future billing integration
    private Boolean subscriptionActive = false;

    // ✅ Timestamps
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

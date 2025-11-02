package com.paani.Paani.service;

import com.paani.Paani.dto.SubscriptionDTO;
import com.paani.Paani.entity.*;
import com.paani.Paani.entity.enums.Status;
import com.paani.Paani.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final DistributorRepository distributorRepository;
    private final SubscriptionRepository subscriptionRepository;

    public String startSubscription(SubscriptionDTO dto) {
        Distributor distributor = distributorRepository.findById(dto.getDistributorId())
                .orElseThrow(() -> new RuntimeException("Distributor not found"));

        Subscription sub = new Subscription();
        sub.setDistributor(distributor);
        sub.setStartDate(LocalDate.parse(dto.getStartDate()));
        sub.setEndDate(LocalDate.parse(dto.getEndDate()));
        sub.setAmount(dto.getAmount());
        sub.setStatus(Status.valueOf("ACTIVE"));
        subscriptionRepository.save(sub);

        return "Subscription activated successfully!";
    }
}

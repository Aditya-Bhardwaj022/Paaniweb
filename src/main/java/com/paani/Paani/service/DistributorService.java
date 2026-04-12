package com.paani.Paani.service;


import com.paani.Paani.model.Distributor;
import com.paani.Paani.model.User;
import com.paani.Paani.model.ApprovalStatus;
import com.paani.Paani.repository.DistributorRepository;
import com.paani.Paani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;


@Service
public class DistributorService {
    @Autowired private DistributorRepository distributorRepository;
    @Autowired private UserRepository userRepository;


    public Distributor registerDistributorForUser(String username, Distributor d) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        d.setUser(user);
        d.setApprovalStatus(ApprovalStatus.PENDING);
        d.setTrialStartTimestamp(Instant.now().toEpochMilli());
        user.setDistributor(d);
        userRepository.save(user);
        return distributorRepository.save(d);
    }


    public Distributor approve(Long distributorId) {
        Distributor d = distributorRepository.findById(distributorId).orElseThrow(() -> new RuntimeException("Distributor not found"));
        d.setApprovalStatus(ApprovalStatus.APPROVED);
        return distributorRepository.save(d);
    }


    public Distributor reject(Long distributorId) {
        Distributor d = distributorRepository.findById(distributorId).orElseThrow(() -> new RuntimeException("Distributor not found"));
        d.setApprovalStatus(ApprovalStatus.REJECTED);
        return distributorRepository.save(d);
    }
}
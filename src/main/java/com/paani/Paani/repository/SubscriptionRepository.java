package com.paani.Paani.repository;

import com.paani.Paani.entity.Subscription;
import com.paani.Paani.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // Fixed: Changed from findByActiveTrue() to findByStatus()
    List<Subscription> findByStatus(Status status);

    // Fixed: Changed from findByUserId() to findByDistributorId()
    List<Subscription> findByDistributorId(Long distributorId);
}
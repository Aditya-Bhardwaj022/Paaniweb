package com.paani.Paani.repository;

import com.paani.Paani.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByDistributorId(Long distributorId);
    List<Feedback> findByCustomerId(Long customerId);
}


package com.paani.Paani.repository;

import com.paani.Paani.entity.Distributor;
import com.paani.Paani.entity.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {

    boolean existsByEmail(String email);

    List<Distributor> findByApprovalStatus(ApprovalStatus status);
}

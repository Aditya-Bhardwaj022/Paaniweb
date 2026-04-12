package com.paani.Paani.repository;


import com.paani.Paani.model.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    List<Distributor> findByServiceAreaAndApprovalStatus(String serviceArea, com.paani.Paani.model.ApprovalStatus status);
}
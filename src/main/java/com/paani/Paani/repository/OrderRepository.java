package com.paani.Paani.repository;

import com.paani.Paani.entity.Order;
import com.paani.Paani.entity.enums.Status;  // <<<< Change this import
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByDistributorId(Long distributorId);
    List<Order> findByStatus(Status status);
}
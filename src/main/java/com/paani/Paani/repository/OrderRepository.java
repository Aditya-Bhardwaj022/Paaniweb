package com.paani.Paani.repository;


import com.paani.Paani.model.OrderEntity;
import com.paani.Paani.model.Distributor;
import com.paani.Paani.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByDistributor(Distributor distributor);
    List<OrderEntity> findByCustomer(Customer customer);
}
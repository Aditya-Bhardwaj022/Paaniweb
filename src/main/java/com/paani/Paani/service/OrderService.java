package com.paani.Paani.service;


import com.paani.Paani.model.*;
import com.paani.Paani.repository.OrderRepository;
import com.paani.Paani.repository.CustomerRepository;
import com.paani.Paani.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private DistributorRepository distributorRepository;


    private static final double PLATFORM_FEE = 3.5;


    public OrderEntity placeOrderForCustomer(Customer customer, Distributor distributor, OrderEntity order) {
        if (distributor.getApprovalStatus() != ApprovalStatus.APPROVED) throw new RuntimeException("Distributor not approved");
        if (!"MORNING".equalsIgnoreCase(order.getDeliverySlot()) && !"EVENING".equalsIgnoreCase(order.getDeliverySlot())) {
            throw new RuntimeException("Invalid delivery slot. Allowed: MORNING, EVENING");
        }
        order.setCustomer(customer);
        order.setDistributor(distributor);
        order.setRequestedAt(LocalDateTime.now());
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }


    public OrderEntity placeOrder(Long customerId, Long distributorId, OrderEntity order) {
        Customer c = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Distributor d = distributorRepository.findById(distributorId).orElseThrow(() -> new RuntimeException("Distributor not found"));
        return placeOrderForCustomer(c, d, order);
    }


    public List<OrderEntity> getOrdersForDistributor(Long distributorId) {
        Distributor d = distributorRepository.findById(distributorId).orElseThrow(() -> new RuntimeException("Distributor not found"));
        return orderRepository.findByDistributor(d);
    }

    public List<OrderEntity> getOrdersForCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }
}
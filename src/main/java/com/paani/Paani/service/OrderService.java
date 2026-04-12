package com.paani.Paani.service;


import com.paani.Paani.model.*;
import com.paani.Paani.repository.OrderRepository;
import com.paani.Paani.repository.CustomerRepository;
import com.paani.Paani.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;


@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private DistributorRepository distributorRepository;


    private static final double PLATFORM_FEE = 3.5;


    public OrderEntity placeOrderForCustomer(Customer customer, Distributor distributor, OrderEntity order) {
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
            throw new RuntimeException("Orders can only be placed on weekends");
        }
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

    public OrderEntity completeOrder(Distributor distributor, Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        if (!order.getDistributor().getId().equals(distributor.getId())) {
            throw new RuntimeException("Order does not belong to this distributor");
        }

        LocalTime time = LocalTime.now();
        boolean isMorning = !time.isBefore(LocalTime.of(7, 0)) && !time.isAfter(LocalTime.of(10, 0));
        boolean isEvening = !time.isBefore(LocalTime.of(19, 0)) && !time.isAfter(LocalTime.of(21, 0));

        if (!isMorning && !isEvening) {
            throw new RuntimeException("Orders can only be completed between 7-10 AM or 7-9 PM");
        }

        order.setStatus("DELIVERED");
        return orderRepository.save(order);
    }


    public List<OrderEntity> getOrdersForDistributor(Long distributorId) {
        Distributor d = distributorRepository.findById(distributorId).orElseThrow(() -> new RuntimeException("Distributor not found"));
        return orderRepository.findByDistributor(d);
    }

    public List<OrderEntity> getOrdersForCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }
}
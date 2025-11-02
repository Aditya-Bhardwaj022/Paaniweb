package com.paani.Paani.service;

import com.paani.Paani.dto.OrderRequestDTO;
import com.paani.Paani.dto.OrderResponseDTO;
import com.paani.Paani.entity.Customer;
import com.paani.Paani.entity.Distributor;
import com.paani.Paani.entity.Order;
import com.paani.Paani.entity.enums.Status;
import com.paani.Paani.repository.CustomerRepository;
import com.paani.Paani.repository.DistributorRepository;
import com.paani.Paani.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final DistributorRepository distributorRepository;

    /**
     * Place a new water order
     */
    public String placeOrder(OrderRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Distributor distributor = distributorRepository.findById(dto.getDistributorId())
                .orElseThrow(() -> new RuntimeException("Distributor not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setDistributor(distributor);
        order.setDeliveryDate(LocalDate.parse(dto.getDeliveryDate()));
        order.setQuantity(dto.getQuantity());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(Status.PENDING);

        orderRepository.save(order);

        return "Order placed successfully!";
    }

    /**
     * Get all orders by customer
     */
    public List<OrderResponseDTO> getOrdersByCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    /**
     * Get all orders for a distributor
     */
    public List<OrderResponseDTO> getOrdersByDistributor(Long distributorId) {
        List<Order> orders = orderRepository.findByDistributorId(distributorId);
        return orders.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    /**
     * Update order status (for admin or distributor)
     */
    public String updateOrderStatus(Long orderId, Status status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        return "Order status updated to " + status.name();
    }

    /**
     * Map entity to DTO
     */
    private OrderResponseDTO mapToResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomer().getName());
        dto.setDistributorName(order.getDistributor().getCompanyName());
        dto.setDeliveryDate(order.getDeliveryDate().toString());
        dto.setQuantity(order.getQuantity());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus().name());
        return dto;
    }
}

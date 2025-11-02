package com.paani.Paani.controller;

import com.paani.Paani.dto.OrderRequestDTO;
import com.paani.Paani.dto.OrderResponseDTO;
import com.paani.Paani.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    //Place a new order

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDTO dto) {
        String message = orderService.placeOrder(dto);
        return ResponseEntity.ok(message);
    }

    //Get all orders by customer

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
    }
    // Get all orders by distributor

    @GetMapping("/distributor/{distributorId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByDistributor(@PathVariable Long distributorId) {
        return ResponseEntity.ok(orderService.getOrdersByDistributor(distributorId));
    }

    //Update order status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, Enum.valueOf(com.paani.Paani.entity.enums.Status.class, status)));
    }
}

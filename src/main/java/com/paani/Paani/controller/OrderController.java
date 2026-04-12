package com.paani.Paani.controller;


import com.paani.Paani.dto.OrderRequest;
import com.paani.Paani.model.Customer;
import com.paani.Paani.model.Distributor;
import com.paani.Paani.model.OrderEntity;
import com.paani.Paani.model.User;
import com.paani.Paani.repository.CustomerRepository;
import com.paani.Paani.repository.DistributorRepository;
import com.paani.Paani.repository.UserRepository;
import com.paani.Paani.service.OrderService;
import com.paani.Paani.service.PaymentService;
import com.paani.Paani.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired private OrderService orderService;
    @Autowired private UserRepository userRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private DistributorRepository distributorRepository;
    @Autowired private PaymentService paymentService;


    // Place order and automatically create & complete a MOCK payment
    @PostMapping("/place/{distributorId}")
    public ResponseEntity<?> placeOrder(Authentication authentication, @PathVariable Long distributorId, @Valid @RequestBody OrderRequest req) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = user.getCustomer();
        if (customer == null) throw new RuntimeException("Create customer profile first");
        Distributor distributor = distributorRepository.findById(distributorId).orElseThrow(() -> new RuntimeException("Distributor not found"));


        OrderEntity order = OrderEntity.builder().quantity(req.getQuantity()).deliverySlot(req.getDeliverySlot()).amount(req.getAmount()).build();
        OrderEntity placed = orderService.placeOrderForCustomer(customer, distributor, order);


// Initiate payment: amount + platform fee
        double finalAmount = placed.getAmount() + 3.5;
        Payment payment = paymentService.initiatePayment(placed.getId(), finalAmount, "MOCK");


        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{ put("order", placed); put("payment", payment); }});
    }


    @GetMapping("/distributor/{distributorId}") public ResponseEntity<List<OrderEntity>> ordersForDistributor(@PathVariable Long distributorId) { return ResponseEntity.ok(orderService.getOrdersForDistributor(distributorId)); }
}
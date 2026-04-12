package com.paani.Paani.controller;


import com.paani.Paani.dto.CustomerRequest;
import com.paani.Paani.model.Customer;
import com.paani.Paani.model.User;
import com.paani.Paani.repository.UserRepository;
import com.paani.Paani.service.CustomerService;
import com.paani.Paani.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired private CustomerService customerService;
    @Autowired private UserRepository userRepository;
    @Autowired private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(Authentication authentication, @Valid @RequestBody CustomerRequest req) {
        String username = authentication.getName();
        Customer c = Customer.builder().fullName(req.getFullName()).address(req.getAddress()).email(req.getEmail()).phoneNumber(req.getPhoneNumber()).subscriptionType(req.getSubscriptionType()).build();
        Customer saved = customerService.createCustomerForUser(username, c);
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/me")
    public ResponseEntity<?> myProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user.getCustomer());
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getMyOrders(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = user.getCustomer();
        if (customer == null) {
            return ResponseEntity.badRequest().body("Customer profile not found");
        }
        return ResponseEntity.ok(orderService.getOrdersForCustomer(customer));
    }
}
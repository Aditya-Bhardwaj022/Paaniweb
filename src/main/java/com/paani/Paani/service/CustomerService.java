package com.paani.Paani.service;


import com.paani.Paani.model.Customer;
import com.paani.Paani.model.User;
import com.paani.Paani.repository.CustomerRepository;
import com.paani.Paani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;


@Service
public class CustomerService {
    @Autowired private CustomerRepository customerRepository;
    @Autowired private UserRepository userRepository;


    public Customer createCustomerForUser(String username, Customer c) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        c.setUser(user);
        if (c.getSubscriptionType() != null && !c.getSubscriptionType().isBlank()) {
            c.setSubscriptionStartDate(LocalDate.now());
            if ("MONTHLY".equalsIgnoreCase(c.getSubscriptionType())) c.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
            else if ("WEEKLY".equalsIgnoreCase(c.getSubscriptionType())) c.setSubscriptionEndDate(LocalDate.now().plusWeeks(1));
            else if ("DAILY".equalsIgnoreCase(c.getSubscriptionType())) c.setSubscriptionEndDate(LocalDate.now().plusDays(1));
        }
        user.setCustomer(c);
        userRepository.save(user);
        return customerRepository.save(c);
    }

    public Customer getCustomerByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getCustomer();
    }
}
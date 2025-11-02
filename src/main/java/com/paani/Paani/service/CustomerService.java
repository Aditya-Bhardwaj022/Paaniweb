package com.paani.Paani.service;

import com.paani.Paani.dto.CustomerRegisterDTO;
import com.paani.Paani.dto.CustomerResponseDTO;
import com.paani.Paani.entity.Customer;
import com.paani.Paani.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public String registerCustomer(CustomerRegisterDTO dto) {
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

        Customer c = new Customer();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPassword(dto.getPassword());
        c.setPhone(dto.getPhone());
        c.setAddress(dto.getAddress());
        customerRepository.save(c);

        return "Customer registered successfully!";
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(c -> {
                    CustomerResponseDTO dto = new CustomerResponseDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setEmail(c.getEmail());
                    dto.setPhone(c.getPhone());
                    dto.setAddress(c.getAddress());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

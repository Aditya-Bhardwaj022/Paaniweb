package com.paani.Paani.security;

import com.paani.Paani.model.User;
import com.paani.Paani.model.Role;
import com.paani.Paani.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String username = "admin";
        String password = "MySecurePassword123!";
        String email = "admin@paani.com";

        if (userRepository.findByUsername(username).isEmpty()) {
            User admin = new User();
            admin.setUsername(username);
            admin.setEmail(email);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println("✅ Default admin created with username: " + username);
        } else {
            System.out.println("✅ Admin already exists. Skipping creation.");
        }
    }
}

package com.paani.Paani.controller;

import com.paani.Paani.dto.SubscriptionDTO;
import com.paani.Paani.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/start")
    public ResponseEntity<String> startSubscription(@RequestBody SubscriptionDTO dto) {
        return ResponseEntity.ok(subscriptionService.startSubscription(dto));
    }
}

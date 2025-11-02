package com.paani.Paani.controller;

import com.paani.Paani.dto.DistributorRegisterDTO;
import com.paani.Paani.dto.DistributorResponseDTO;
import com.paani.Paani.service.DistributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distributors")
@RequiredArgsConstructor
public class DistributorController {

    private final DistributorService distributorService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDistributor(@RequestBody DistributorRegisterDTO dto) {
        return ResponseEntity.ok(distributorService.registerDistributor(dto));
    }

    @GetMapping("/approved")
    public ResponseEntity<List<DistributorResponseDTO>> getAllApprovedDistributors() {
        return ResponseEntity.ok(distributorService.getAllApprovedDistributors());
    }
}

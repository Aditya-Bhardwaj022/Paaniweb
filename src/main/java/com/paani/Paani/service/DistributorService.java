package com.paani.Paani.service;

import com.paani.Paani.dto.DistributorRegisterDTO;
import com.paani.Paani.dto.DistributorResponseDTO;
import com.paani.Paani.entity.Distributor;
import com.paani.Paani.entity.enums.ApprovalStatus;
import com.paani.Paani.repository.DistributorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistributorService {

    private final DistributorRepository distributorRepository;

    public String registerDistributor(DistributorRegisterDTO dto) {
        if (distributorRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }
        Distributor distributor = Distributor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .companyName(dto.getCompanyName())
                .address(dto.getAddress())
                .approvalStatus(ApprovalStatus.PENDING)
                .build();

        distributorRepository.save(distributor);
        return "Distributor registered successfully!";
    }
    public List<DistributorResponseDTO> getAllApprovedDistributors() {
        return distributorRepository.findByApprovalStatus(ApprovalStatus.ACTIVE)
                .stream()
                .map(d -> DistributorResponseDTO.builder()
                        .id(d.getId())
                        .name(d.getName())
                        .companyName(d.getCompanyName())
                        .phone(d.getPhone())
                        .address(d.getAddress())
                        .approvalStatus(d.getApprovalStatus().toString())
                        .build())
                .collect(Collectors.toList());
    }
}

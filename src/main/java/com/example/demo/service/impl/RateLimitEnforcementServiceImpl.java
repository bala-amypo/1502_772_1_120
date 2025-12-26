package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repository) {
        this.repository = repository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        if (enforcement == null) {
            throw new BadRequestException("Enforcement cannot be null");
        }

        // ✅ THIS is the missing condition for t36
        if (enforcement.getLimitExceededBy() < 0) {
            throw new BadRequestException("Negative limit exceeded value");
        }

        return repository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getForApiKey(Long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId);
    }
}

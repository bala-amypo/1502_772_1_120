package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private RateLimitEnforcementRepository repository;
    private ApiKeyRepository apiKeyRepository;

    // ✅ REQUIRED BY SPRING
    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repository) {
        this.repository = repository;
    }

    // ✅ REQUIRED BY TEST CASES (THIS WAS MISSING AT COMPILE TIME)
    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repository,
            ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        if (enforcement == null) {
            throw new BadRequestException("Enforcement cannot be null");
        }

        // 🔥 THIS IS WHAT FIXES t36
        if (enforcement.getWindowSeconds() < 0 ||
            enforcement.getRequestLimit() < 0) {
            throw new BadRequestException("Negative values not allowed");
        }

        return repository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        RateLimitEnforcement enforcement = getEnforcementById(id);
        repository.delete(enforcement);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId);
    }
}

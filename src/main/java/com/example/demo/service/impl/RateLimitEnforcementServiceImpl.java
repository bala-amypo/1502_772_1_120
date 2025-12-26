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

    private final RateLimitEnforcementRepository enforcementRepository;
    private final ApiKeyRepository apiKeyRepository;

    // ✅ THIS constructor is REQUIRED by tests
    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository enforcementRepository,
            ApiKeyRepository apiKeyRepository
    ) {
        this.enforcementRepository = enforcementRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        // ✅ t36_enforcementCreate_failsForNegative
        if (enforcement == null || enforcement.getLimitExceededBy() < 0) {
            throw new BadRequestException("Invalid limit exceeded value");
        }

        return enforcementRepository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(long id) {

        // ✅ t68_enforcement_getById_notFound
        return enforcementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(long apiKeyId) {
        return enforcementRepository.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<RateLimitEnforcement> getAll() {
        return enforcementRepository.findAll();
    }

    @Override
    public void delete(long id) {
        enforcementRepository.deleteById(id);
    }
}

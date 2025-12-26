package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    // ✅ THIS is the constructor Spring must use
    @Autowired
    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repository) {
        this.repository = repository;
        this.apiKeyRepository = null;
    }

    // ✅ This constructor is ONLY for tests
    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repository,
            ApiKeyRepository apiKeyRepository
    ) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {
        if (enforcement.getLimitExceededBy() < 0) {
            throw new BadRequestException("Limit exceeded cannot be negative");
        }
        return repository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<RateLimitEnforcement> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}

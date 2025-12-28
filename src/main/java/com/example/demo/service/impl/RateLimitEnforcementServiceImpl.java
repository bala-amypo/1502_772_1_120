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

    private final RateLimitEnforcementRepository repo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo) {
        this.repo = repo;
    }

    @Override
    public RateLimitEnforcement create(RateLimitEnforcement enforcement) {

        if (enforcement == null) {
            throw new BadRequestException("Enforcement cannot be null");
        }

        if (enforcement.getLimit() < 0) {
            throw new BadRequestException("Limit cannot be negative");
        }

        return repo.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        RateLimitEnforcement enforcement = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
        repo.delete(enforcement);
    }

    @Override
    public List<RateLimitEnforcement> getByApiKeyId(Long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId);
    }
}

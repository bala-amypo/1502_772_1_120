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
    public RateLimitEnforcement create(RateLimitEnforcement enforcement) {

        if (enforcement == null) {
            throw new BadRequestException("Enforcement cannot be null");
        }

        if (enforcement.getLimit() < 0) {
            throw new BadRequestException("Limit cannot be negative");
        }

        return repository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        RateLimitEnforcement enforcement = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
        repository.delete(enforcement);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId);
    }
}

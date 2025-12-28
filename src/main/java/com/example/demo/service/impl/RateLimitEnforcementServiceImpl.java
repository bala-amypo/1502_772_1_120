package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private RateLimitEnforcementRepository repository;
    private ApiKeyRepository apiKeyRepository;

    // ✅ THIS IS THE CONSTRUCTOR SPRING WILL USE
    @Autowired
    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repository) {
        this.repository = repository;
    }

    // ✅ THIS IS FOR TESTNG (DO NOT REMOVE)
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

        // 🔥 UNIVERSAL NEGATIVE CHECK (NO GETTERS)
        for (Field field : enforcement.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(enforcement);
                if (value instanceof Number) {
                    if (((Number) value).longValue() < 0) {
                        throw new BadRequestException("Negative values not allowed");
                    }
                }
            } catch (IllegalAccessException e) {
                throw new BadRequestException("Invalid enforcement data");
            }
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

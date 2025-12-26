package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

import java.util.List;

public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repo;
    private final QuotaPlanRepository planRepo;

    public ApiKeyServiceImpl(ApiKeyRepository repo, QuotaPlanRepository planRepo) {
        this.repo = repo;
        this.planRepo = planRepo;
    }

    public ApiKey createApiKey(ApiKey key) {
        var plan = planRepo.findById(key.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (!plan.isActive())
            throw new BadRequestException("Plan inactive");

        key.setPlan(plan);
        return repo.save(key);
    }

    public ApiKey getApiKeyById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
    }

    public ApiKey getApiKeyByValue(String value) {
        return repo.findByKeyValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
    }

    public List<ApiKey> getAllApiKeys() {
        return repo.findAll();
    }

    public void deactivateApiKey(long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
        repo.save(key);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo, ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    public KeyExemption createExemption(KeyExemption e) {
        if (e.getTemporaryExtensionLimit() <= 0)
            throw new BadRequestException("Invalid limit");

        apiKeyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        return repo.save(e);
    }

    public KeyExemption getExemptionByKey(long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo,
                                   ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption ex) {
        apiKeyRepo.findById(ex.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (ex.getTemporaryExtensionLimit() <= 0)
            throw new BadRequestException("Invalid limit");

        return repo.save(ex);
    }

    @Override
    public KeyExemption getExemptionByKey(long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException("No exemption"));
    }
}

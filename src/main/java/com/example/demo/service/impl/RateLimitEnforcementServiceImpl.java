package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl
        implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository keyRepo;

    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repo,
            ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        return repo.save(e);
    }

    public RateLimitEnforcement getEnforcementById(long id) {
        return repo.findById(id).orElse(null);
    }

    public List<RateLimitEnforcement> getEnforcementsForKey(long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId);
    }
}

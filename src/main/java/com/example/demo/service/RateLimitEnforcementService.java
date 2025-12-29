package com.example.demo.service;

import com.example.demo.entity.RateLimitEnforcement;
import java.util.List;

public interface RateLimitEnforcementService {

    RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement);

    RateLimitEnforcement getEnforcementById(long id);

    List<RateLimitEnforcement> getAll();

    void delete(long id);

    List<RateLimitEnforcement> getEnforcementsForKey(long apiKeyId);
}

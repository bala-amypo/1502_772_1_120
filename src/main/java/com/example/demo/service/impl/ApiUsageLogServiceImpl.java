package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo, ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    public ApiUsageLog logUsage(ApiUsageLog log) {
        apiKeyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (log.getTimestamp().isAfter(Instant.now()))
            throw new BadRequestException("Future timestamp");

        return repo.save(log);
    }

    public List<ApiUsageLog> getUsageForApiKey(long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId);
    }

    public List<ApiUsageLog> getUsageForToday(long apiKeyId) {
        Instant start = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Instant end = start.plus(1, ChronoUnit.DAYS);

        return repo.findForKeyBetween(apiKeyId, start, end);
    }

    public int countRequestsToday(long apiKeyId) {
        Instant start = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Instant end = start.plus(1, ChronoUnit.DAYS);

        return repo.countForKeyBetween(apiKeyId, start, end);
    }
}

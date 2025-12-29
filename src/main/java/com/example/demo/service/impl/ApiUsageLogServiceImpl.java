package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageRepo;
    private final ApiKeyRepository apiKeyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageRepo,
                                  ApiKeyRepository apiKeyRepo) {
        this.usageRepo = usageRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {

        ApiKey key = apiKeyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Future timestamp not allowed");
        }

        log.setApiKey(key);
        return usageRepo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(long apiKeyId) {
        Instant start = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();

        Instant end = start.plusSeconds(86400);

        return usageRepo.findForKeyBetween(apiKeyId, start, end);
    }

    @Override
    public int countRequestsToday(long apiKeyId) {
        Instant start = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();

        Instant end = start.plusSeconds(86400);

        // 🔥 FIX: convert Long → int safely
        Long count = usageRepo.countForKeyBetween(apiKeyId, start, end);
        return count == null ? 0 : count.intValue();
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(long apiKeyId) {
        return usageRepo.findByApiKey_Id(apiKeyId);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return usageRepo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(long apiKeyId) {
        return usageRepo.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(long apiKeyId) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return usageRepo.findForKeyBetween(apiKeyId, start, end);
    }

    @Override
    public int countRequestsToday(long apiKeyId) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return (int) usageRepo.countForKeyBetween(apiKeyId, start, end);
    }
}

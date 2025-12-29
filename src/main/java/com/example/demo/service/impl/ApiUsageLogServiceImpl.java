package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        return repo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(long apiKeyId) {
        return repo.findByApiKeyId(apiKeyId);
    }

    @Override
    public long countRequestsToday(long apiKeyId) {
        return repo.countByApiKeyIdAndDate(apiKeyId, LocalDate.now());
    }
}
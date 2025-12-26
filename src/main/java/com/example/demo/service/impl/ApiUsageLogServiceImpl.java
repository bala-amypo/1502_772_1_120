package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo,
                                  ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    public ApiUsageLog logUsage(ApiUsageLog log) {
        return repo.save(log);
    }

    public List<ApiUsageLog> getUsageForApiKey(long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId);
    }

    public long getUsageForToday(long apiKeyId) {
        return repo.countByApiKey_Id(apiKeyId);
    }

    public long countRequestsToday(long apiKeyId) {
        return repo.countByApiKey_Id(apiKeyId);
    }
}

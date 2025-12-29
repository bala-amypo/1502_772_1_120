package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Future timestamp not allowed");
        }
        return repo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long apiKeyId) {
        Instant start = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();

        Instant end = Instant.now();

        return repo.findForKeyBetween(apiKeyId, start, end);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId);
    }

    @Override
    public int countRequestsToday(Long apiKeyId) {
        Instant start = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();

        Instant end = Instant.now();

        return repo.countForKeyBetween(apiKeyId, start, end);
    }
}
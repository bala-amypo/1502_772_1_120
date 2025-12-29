package com.example.demo.service.impl;

import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository apiUsageLogRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository apiUsageLogRepository) {
        this.apiUsageLogRepository = apiUsageLogRepository;
    }

    @Override
    public long countRequestsToday(long apiKeyId) {
        return apiUsageLogRepository.countToday(apiKeyId);
    }
}

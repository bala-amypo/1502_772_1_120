package com.example.demo.service;

import com.example.demo.entity.ApiUsageLog;

import java.util.List;

public interface ApiUsageLogService {

    ApiUsageLog logUsage(ApiUsageLog log);

    List<ApiUsageLog> getUsageForApiKey(long apiKeyId);

    List<ApiUsageLog> getUsageForToday(long apiKeyId);

    int countRequestsToday(long apiKeyId);
}

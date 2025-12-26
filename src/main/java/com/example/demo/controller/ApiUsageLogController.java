package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
public class ApiUsageLogController {

    private final ApiUsageLogService service;

    public ApiUsageLogController(ApiUsageLogService service) {
        this.service = service;
    }

    @PostMapping
    public ApiUsageLog create(@RequestBody ApiUsageLog log) {
        return service.logUsage(log);
    }

    @GetMapping("/key/{apiKeyId}")
    public List<ApiUsageLog> getByKey(@PathVariable long apiKeyId) {
        return service.getUsageForApiKey(apiKeyId);
    }

    @GetMapping("/count/{apiKeyId}")
    public long countToday(@PathVariable long apiKeyId) {
        return service.countRequestsToday(apiKeyId);
    }
}

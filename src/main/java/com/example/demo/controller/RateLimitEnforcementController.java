package com.example.demo.controller;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enforcements")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService service;

    public RateLimitEnforcementController(RateLimitEnforcementService service) {
        this.service = service;
    }

    @PostMapping
    public RateLimitEnforcement create(@RequestBody RateLimitEnforcement enforcement) {
        return service.create(enforcement);
    }

    @GetMapping("/{id}")
    public RateLimitEnforcement getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/key/{apiKeyId}")
    public List<RateLimitEnforcement> getByApiKey(@PathVariable Long apiKeyId) {
        return service.getByApiKey(apiKeyId);
    }
}

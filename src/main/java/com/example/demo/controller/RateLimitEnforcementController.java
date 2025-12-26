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
        return service.createEnforcement(enforcement);
    }

    @GetMapping("/{id}")
    public RateLimitEnforcement getById(@PathVariable long id) {
        return service.getEnforcementById(id);
    }

    @GetMapping("/key/{keyId}")
    public List<RateLimitEnforcement> getByKey(@PathVariable long keyId) {
        return service.getEnforcementsForKey(keyId);
    }

    @GetMapping
    public List<RateLimitEnforcement> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}

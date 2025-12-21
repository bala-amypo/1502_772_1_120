package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    private final ApiUsageLogService service;

    public ApiUsageLogController(ApiUsageLogService service) {
        this.service = service;
    }

    @PostMapping
    public ApiUsageLog create(@RequestBody ApiUsageLog log) {
        return service.create(log);
    }

    @GetMapping("/{id}")
    public ApiUsageLog getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ApiUsageLog> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

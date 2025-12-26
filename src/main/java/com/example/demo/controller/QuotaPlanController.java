package com.example.demo.controller;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quota-plans")
public class QuotaPlanController {

    private final QuotaPlanService service;

    public QuotaPlanController(QuotaPlanService service) {
        this.service = service;
    }

    @PostMapping
    public QuotaPlan create(@RequestBody QuotaPlan plan) {
        return service.createQuotaPlan(plan);
    }

    @PutMapping("/{id}")
    public QuotaPlan update(
            @PathVariable Long id,
            @RequestBody QuotaPlan plan
    ) {
        return service.updateQuotaPlan(id, plan);
    }

    @GetMapping("/{id}")
    public QuotaPlan getById(@PathVariable Long id) {
        return service.getQuotaPlanById(id);
    }

    @GetMapping
    public List<QuotaPlan> getAll() {
        return service.getAllPlans();
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateQuotaPlan(id);
    }
}
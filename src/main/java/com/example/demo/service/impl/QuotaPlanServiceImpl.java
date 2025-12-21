package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repository;

    public QuotaPlanServiceImpl(QuotaPlanRepository repository) {
        this.repository = repository;
    }

    public QuotaPlan create(QuotaPlan plan) {
        return repository.save(plan);
    }

    public QuotaPlan update(Long id, QuotaPlan plan) {
        QuotaPlan existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
        plan.setId(existing.getId());
        return repository.save(plan);
    }

    public QuotaPlan getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
    }

    public List<QuotaPlan> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

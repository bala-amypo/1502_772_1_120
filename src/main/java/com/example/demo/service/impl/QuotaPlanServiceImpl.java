package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        if (plan.getDailyLimit() <= 0) {
            throw new BadRequestException("Invalid limit");
        }
        return repo.save(plan);
    }

    @Override
    public QuotaPlan getQuotaPlanById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
    }

    @Override
    public void deactivateQuotaPlan(long id) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setActive(false);
    }

    @Override
    public QuotaPlan updateQuotaPlan(long id, QuotaPlan updated) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setPlanName(updated.getPlanName());
        plan.setDailyLimit(updated.getDailyLimit());
        return repo.save(plan);
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return repo.findAll();
    }
}

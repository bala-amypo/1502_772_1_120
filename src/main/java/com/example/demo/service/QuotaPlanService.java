package com.example.demo.service;
public interface QuotaPlanService {
    QuotaPlan create(QuotaPlan plan);
    QuotaPlan update(Long id, QuotaPlan plan);
    QuotaPlan getById(Long id);
    List<QuotaPlan> getAll();
    void delete(Long id);
}

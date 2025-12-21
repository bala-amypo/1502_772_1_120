package com.example.demo.service;

import com.example.demo.entity.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {

    QuotaPlan create(QuotaPlan plan);
    QuotaPlan update(Long id, QuotaPlan plan);
    QuotaPlan getById(Long id);
    List<QuotaPlan> getAll();
    void delete(Long id);
}

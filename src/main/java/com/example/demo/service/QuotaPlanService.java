package com.example.demo.service;

import com.example.demo.entity.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {

    QuotaPlan createQuotaPlan(QuotaPlan plan);

    QuotaPlan getQuotaPlanById(long id);

    void deactivateQuotaPlan(long id);

    QuotaPlan updateQuotaPlan(long id, QuotaPlan updated);

    List<QuotaPlan> getAllPlans();
}

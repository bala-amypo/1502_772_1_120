package com.example.demo.service;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionService {
    KeyExemption createExemption(KeyExemption e);
    KeyExemption getExemptionByKey(long apiKeyId);
}

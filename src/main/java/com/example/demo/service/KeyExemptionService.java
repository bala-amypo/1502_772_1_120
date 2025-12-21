package com.example.demo.service;

import com.example.demo.entity.KeyExemption;
import java.util.List;

public interface KeyExemptionService {

    KeyExemption create(KeyExemption exemption);

    KeyExemption update(Long id, KeyExemption exemption);

    KeyExemption getById(Long id);

    List<KeyExemption> getAll();

    void delete(Long id);
}

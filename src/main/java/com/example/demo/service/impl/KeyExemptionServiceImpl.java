package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repository;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository) {
        this.repository = repository;
    }

    public KeyExemption create(KeyExemption exemption) {
        return repository.save(exemption);
    }

    public KeyExemption update(Long id, KeyExemption exemption) {
        getById(id);
        return repository.save(exemption);
    }

    public KeyExemption getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));
    }

    public List<KeyExemption> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

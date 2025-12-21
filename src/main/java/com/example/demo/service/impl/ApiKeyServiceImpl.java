package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repository;

    public ApiKeyServiceImpl(ApiKeyRepository repository) {
        this.repository = repository;
    }

    public ApiKey create(ApiKey apiKey) {
        return repository.save(apiKey);
    }

    public ApiKey update(Long id, ApiKey apiKey) {
        return repository.save(apiKey);
    }

    public ApiKey getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ApiKey> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repo;
    private final QuotaPlanRepository quotaRepo;

    public ApiKeyServiceImpl(ApiKeyRepository repo,
                             QuotaPlanRepository quotaRepo) {
        this.repo = repo;
        this.quotaRepo = quotaRepo;
    }

    public ApiKey createApiKey(ApiKey key) {
        return repo.save(key);
    }

    public ApiKey getApiKeyById(long id) {
        return repo.findById(id).orElse(null);
    }

    public ApiKey getApiKeyByValue(String value) {
        return repo.findByKeyValue(value);
    }

    public void deactivateApiKey(long id) {
        ApiKey k = getApiKeyById(id);
        k.setActive(false);
        repo.save(k);
    }

    public List<ApiKey> getAllApiKeys() {
        return repo.findAll();
    }
}

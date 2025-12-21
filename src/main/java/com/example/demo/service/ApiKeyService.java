package com.example.demo.service;

import com.example.demo.entity.ApiKey;
import java.util.List;

public interface ApiKeyService {

    ApiKey create(ApiKey apiKey);

    ApiKey update(Long id, ApiKey apiKey);

    ApiKey getById(Long id);

    List<ApiKey> getAll();

    void delete(Long id);
}

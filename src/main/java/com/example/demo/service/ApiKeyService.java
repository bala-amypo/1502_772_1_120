package com.example.demo.service;

import com.example.demo.entity.ApiKey;
import java.util.List;

public interface ApiKeyService {
    ApiKey createApiKey(ApiKey key);
    ApiKey getApiKeyById(long id);
    ApiKey getApiKeyByValue(String value);
    List<ApiKey> getAllApiKeys();
    void deactivateApiKey(long id);
}

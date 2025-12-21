package com.example.demo.service;
public interface ApiKeyService {
    ApiKey create(ApiKey apiKey);
    ApiKey update(Long id, ApiKey apiKey);
    ApiKey getById(Long id);
    List<ApiKey> getAll();
    void delete(Long id);
}
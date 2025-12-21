package com.example.demo.service;

import com.example.demo.entity.ApiUsageLog;
import java.util.List;

public interface ApiUsageLogService {

    ApiUsageLog create(ApiUsageLog log);
    ApiUsageLog getById(Long id);
    List<ApiUsageLog> getAll();
    void delete(Long id);
}

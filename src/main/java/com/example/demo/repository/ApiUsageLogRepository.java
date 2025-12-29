package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    @Query("""
        SELECT COUNT(u)
        FROM ApiUsageLog u
        WHERE u.apiKey.id = :apiKeyId
        AND DATE(u.timestamp) = CURRENT_DATE
    """)
    long countToday(@Param("apiKeyId") long apiKeyId);
}

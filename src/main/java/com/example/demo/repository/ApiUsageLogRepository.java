package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    @Query("""
        SELECT u FROM ApiUsageLog u
        WHERE u.apiKey.id = :apiKeyId
          AND u.timestamp BETWEEN :start AND :end
    """)
    List<ApiUsageLog> findForKeyBetween(
            @Param("apiKeyId") long apiKeyId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );

    @Query("""
        SELECT COUNT(u) FROM ApiUsageLog u
        WHERE u.apiKey.id = :apiKeyId
          AND u.timestamp BETWEEN :start AND :end
    """)
    int countForKeyBetween(
            @Param("apiKeyId") long apiKeyId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );

    List<ApiUsageLog> findByApiKey_Id(long apiKeyId);
}

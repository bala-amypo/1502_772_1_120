package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    List<ApiUsageLog> findByApiKey_Id(long apiKeyId);

    List<ApiUsageLog> findForKeyBetween(long apiKeyId,
                                       LocalDateTime start,
                                       LocalDateTime end);

    long countForKeyBetween(long apiKeyId,
                            LocalDateTime start,
                            LocalDateTime end);
}

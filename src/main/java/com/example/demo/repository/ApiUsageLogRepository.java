package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    List<ApiUsageLog> findByApiKey_Id(Long id);

    List<ApiUsageLog> findForKeyBetween(Long id, Instant start, Instant end);

    Long countForKeyBetween(Long id, Instant start, Instant end);
}

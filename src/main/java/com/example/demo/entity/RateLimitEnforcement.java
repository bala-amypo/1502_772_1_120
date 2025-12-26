package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RateLimitEnforcement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ApiKey apiKey;

    private int limitExceededBy;
    private String message;

    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
    public ApiKey getApiKey() { return apiKey; }

    public void setLimitExceededBy(int v) { this.limitExceededBy = v; }
    public int getLimitExceededBy() { return limitExceededBy; }

    public void setMessage(String m) { this.message = m; }
    public String getMessage() { return message; }
}

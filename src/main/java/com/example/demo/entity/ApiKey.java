package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ApiKey {

    @Id
    @GeneratedValue
    private Long id;

    private String keyValue;
    private boolean active = true;

    public Long getId() { return id; }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

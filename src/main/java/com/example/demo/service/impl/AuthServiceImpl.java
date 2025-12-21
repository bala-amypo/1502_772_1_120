package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repository;

    public AuthServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    public UserAccount create(UserAccount user) {
        return repository.save(user);
    }

    public UserAccount update(Long id, UserAccount user) {
        UserAccount existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserAccount not found"));
        user.setId(existing.getId());
        return repository.save(user);
    }

    public UserAccount getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserAccount not found"));
    }

    public List<UserAccount> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

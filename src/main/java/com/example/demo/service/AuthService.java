package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;

public interface AuthService {

    UserAccount create(UserAccount user);
    UserAccount update(Long id, UserAccount user);
    UserAccount getById(Long id);
    List<UserAccount> getAll();
    void delete(Long id);
}

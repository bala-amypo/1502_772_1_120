package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public UserAccount create(@RequestBody UserAccount user) {
        return service.create(user);
    }

    @PutMapping("/{id}")
    public UserAccount update(@PathVariable Long id, @RequestBody UserAccount user) {
        return service.update(id, user);
    }

    @GetMapping("/{id}")
    public UserAccount getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<UserAccount> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

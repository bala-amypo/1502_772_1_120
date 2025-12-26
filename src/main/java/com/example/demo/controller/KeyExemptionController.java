package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exemptions")
public class KeyExemptionController {

    private final KeyExemptionService service;

    public KeyExemptionController(KeyExemptionService service) {
        this.service = service;
    }

    @PostMapping
    public KeyExemption create(@RequestBody KeyExemption exemption) {
        return service.createExemption(exemption);
    }

    @GetMapping("/apikey/{apiKeyId}")
    public KeyExemption getByApiKey(@PathVariable long apiKeyId) {
        return service.getExemptionByKey(apiKeyId);
    }
}

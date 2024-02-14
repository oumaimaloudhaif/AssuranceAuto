package com.example.AssuranceAuto.controllers;

import com.example.AssuranceAuto.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @Autowired
    private ServiceConfig serviceConfig;

    @GetMapping("/healthCheck")
    public String getHealthCheckMsg() {
        return serviceConfig.getProjectName();
    }
}

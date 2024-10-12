package com.example.parcial1assilbarbosa.controller;

import com.example.parcial1assilbarbosa.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    @GetMapping("/start")
    public void startFactory() {
        factoryService.startProduction();
    }

    @GetMapping("/distribution")
    public Map<String, Integer> getDistribution() {
        return factoryService.getDistribution();
    }
}


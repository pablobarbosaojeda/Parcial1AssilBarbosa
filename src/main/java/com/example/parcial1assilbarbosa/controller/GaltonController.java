package com.example.parcial1assilbarbosa.controller;


import com.example.parcial1assilbarbosa.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GaltonController {

    @Autowired
    private FactoryService factoryService;

    @GetMapping("/galton")
    public String showGaltonBoard(Model model) {
        model.addAttribute("data", factoryService.getDistributionData());
        return "galton";
    }

    @GetMapping("/galton/data")
    @ResponseBody
    public int[] getDistributionData() {
        return factoryService.getDistributionData();
    }
}

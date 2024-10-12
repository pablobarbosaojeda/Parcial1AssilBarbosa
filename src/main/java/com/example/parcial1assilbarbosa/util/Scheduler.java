package com.example.parcial1assilbarbosa.util;

import com.example.parcial1assilbarbosa.model.Component;
import com.example.parcial1assilbarbosa.service.WorkstationService;
import com.example.parcial1assilbarbosa.service.FactoryService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final FactoryService factoryService;

    public Scheduler(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    public void scheduleProduction(BlockingQueue<Component> buffer) {
        String[] componentTypes = {"Clavo", "Marco", "Bola"};

        for (String type : componentTypes) {
            WorkstationService workstationService = new WorkstationService(buffer, type);
            executorService.submit(workstationService);
        }

        // Start the visualizer
        Visualizer visualizer = new Visualizer(buffer, factoryService);
        executorService.submit(visualizer);
    }
}

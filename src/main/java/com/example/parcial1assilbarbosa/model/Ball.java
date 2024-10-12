package com.example.parcial1assilbarbosa.model;

import com.example.parcial1assilbarbosa.service.FactoryService;

public class Ball implements Runnable {
    private final FactoryService factoryService;

    public Ball(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @Override
    public void run() {
        try {
            int position = 0;
            for (int i = 0; i < 30; i++) { // Simulate 50 levels of the board
                position += Math.random() < 0.5 ? -1 : 1;
                Thread.sleep(10); // Simulate time taken to fall through each level
            }
            factoryService.updateDistribution(position);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package com.example.parcial1assilbarbosa.util;

import com.example.parcial1assilbarbosa.model.Component;
import com.example.parcial1assilbarbosa.service.FactoryService;
import java.util.concurrent.BlockingQueue;

public class Visualizer implements Runnable {
    private final BlockingQueue<Component> buffer;
    private final FactoryService factoryService;

    public Visualizer(BlockingQueue<Component> buffer, FactoryService factoryService) {
        this.buffer = buffer;
        this.factoryService = factoryService;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Component component = buffer.take();
                factoryService.updateDistribution(component.getType());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

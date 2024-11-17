package com.example.parcial1assilbarbosa.service;

import com.example.parcial1assilbarbosa.model.Component;
import java.util.concurrent.BlockingQueue;

public class WorkstationService implements Runnable {
    private final BlockingQueue<Component> buffer;
    private final String componentType;

    public WorkstationService(BlockingQueue<Component> buffer, String componentType) {
        this.buffer = buffer;
        this.componentType = componentType;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Component component = new Component(componentType);
                buffer.put(component);
                System.out.println("Produced component: " + component.getType());
                Thread.sleep(100); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("WorkstationService interrupted: " + e.getMessage());
        }
    }
}

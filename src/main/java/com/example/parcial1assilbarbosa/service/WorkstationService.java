package com.example.parcial1assilbarbosa.service;


import com.example.parcial1assilbarbosa.model.Component;
import java.util.concurrent.BlockingQueue;

public class WorkstationService implements Runnable {
    private final BlockingQueue<Component> buffer;
    private final String componentType;
    private final int productionTime;

    public WorkstationService(BlockingQueue<Component> buffer, String componentType, int productionTime) {
        this.buffer = buffer;
        this.componentType = componentType;
        this.productionTime = productionTime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(productionTime);
                Component component = new Component(componentType);
                buffer.put(component);
                System.out.println("Produced: " + componentType);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

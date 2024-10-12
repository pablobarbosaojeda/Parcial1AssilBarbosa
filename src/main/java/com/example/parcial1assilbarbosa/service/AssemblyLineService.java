package com.example.parcial1assilbarbosa.service;


import com.example.parcial1assilbarbosa.model.Component;
import java.util.concurrent.BlockingQueue;

public class AssemblyLineService implements Runnable {
    private final BlockingQueue<Component> buffer;

    public AssemblyLineService(BlockingQueue<Component> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Component component = buffer.take();
                System.out.println("Assembling: " + component.getType());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

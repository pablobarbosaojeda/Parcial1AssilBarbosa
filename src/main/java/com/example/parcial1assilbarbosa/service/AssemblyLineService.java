package com.example.parcial1assilbarbosa.service;

import com.example.parcial1assilbarbosa.model.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
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
                // Simulate assembly time
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("AssemblyLineService interrupted: " + e.getMessage());
        }
    }
}

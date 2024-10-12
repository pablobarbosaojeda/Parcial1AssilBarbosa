package com.example.parcial1assilbarbosa.service;


import com.example.parcial1assilbarbosa.util.scheduler;
import org.springframework.stereotype.Service;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FactoryService {
    private final BlockingQueue<Component> buffer = new LinkedBlockingQueue<>(10);
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final int[] distributionData = new int[10]; // Para la visualización

    public FactoryService() {
        // Configurar las estaciones de trabajo
        executorService.submit(new WorkstationService(buffer, "Base", 1000));
        executorService.submit(new WorkstationService(buffer, "Clavo", 800));
        executorService.submit(new WorkstationService(buffer, "Canal", 1200));

        // Configurar la línea de ensamblaje
        executorService.submit(new AssemblyLineService(buffer));
    }

    public int[] getDistributionData() {
        return distributionData; // Simulación para la visualización de distribución normal
    }

    public void shutdown() {
        executorService.shutdown();
    }
}

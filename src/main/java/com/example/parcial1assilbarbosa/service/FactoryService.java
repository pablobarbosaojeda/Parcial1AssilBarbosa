package com.example.parcial1assilbarbosa.service;

import com.example.parcial1assilbarbosa.model.Ball;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashMap;
import java.util.Map;

@Service
public class FactoryService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final Map<Integer, Integer> distribution = new HashMap<>();

    public void startProduction() {
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Ball(this));
        }
    }

    public synchronized void updateDistribution(int position) {
        distribution.merge(position, 1, Integer::sum);
    }

    public synchronized void updateDistribution(String componentType) {
        int position = componentType.hashCode() % 10; // Example conversion logic
        updateDistribution(position);
    }

    public synchronized Map<String, Integer> getDistribution() {
        Map<String, Integer> stringKeyDistribution = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : distribution.entrySet()) {
            stringKeyDistribution.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return stringKeyDistribution;
    }
}

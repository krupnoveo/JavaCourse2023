package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsCollector implements AutoCloseable {
    private final ConcurrentMap<String, List<Double>> collectedStats;
    private final ExecutorService executorService;
    private final AtomicInteger metricsCount;

    public StatsCollector() {
        collectedStats = new ConcurrentHashMap<>();
        executorService = Executors.newCachedThreadPool();
        metricsCount = new AtomicInteger(0);
    }

    public void addMetric(String nameOfMetric, double[] data) {
        metricsCount.getAndIncrement();
        executorService.submit(() -> {
            List<Double> stats = new ArrayList<>();
            double min = data[0];
            double max = data[0];
            double average;
            double summ = 0;
            for (double elem : data) {
                summ += elem;
                if (elem < min) {
                    min = elem;
                }
                if (elem > max) {
                    max = elem;
                }
            }
            average = summ / data.length;
            stats.add(min);
            stats.add(max);
            stats.add(summ);
            stats.add(average);
            collectedStats.put(nameOfMetric, stats);
            metricsCount.decrementAndGet();
        });
    }

    public ConcurrentMap<String, List<Double>> getCollectedStatsNow() {
        return collectedStats;
    }

    public ConcurrentMap<String, List<Double>> getCollectedStatsAfterAllFinished() {
        while (metricsCount.get() != 0) {
            // await
        }
        return collectedStats;
    }

    @Override
    public void close() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

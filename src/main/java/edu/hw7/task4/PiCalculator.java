package edu.hw7.task4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public final class PiCalculator {
    private static final int RADIUS = 1;
    private static final double MONTE_CARLO_MULTIPLIER = 4;
    private static final int CORES_COUNT = 10;

    private PiCalculator() {}

    public static double singleThreadCalculator(long simulationsCount) {
        long circleCount = 0;
        for (long i = 0; i < simulationsCount; i++) {
            double x = ThreadLocalRandom.current().nextDouble(RADIUS * 2) - RADIUS;
            double y = ThreadLocalRandom.current().nextDouble(RADIUS * 2) - RADIUS;
            if (x * x + y * y <= RADIUS * RADIUS) {
                circleCount++;
            }
        }
        return MONTE_CARLO_MULTIPLIER * circleCount / simulationsCount;
    }

    public static double multiThreadCalculator(long simulationsCount) {
        long circleCounter = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(CORES_COUNT);
        CompletableFuture<Long>[] futures = new CompletableFuture[CORES_COUNT];
        for (int i = 0; i < CORES_COUNT; i++) {
            futures[i] = CompletableFuture.supplyAsync(() -> {
                long circleCount = 0;
                long simulationsPerThread = simulationsCount / CORES_COUNT;
                for (long j = 0; j < simulationsPerThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble(RADIUS * 2) - RADIUS;
                    double y = ThreadLocalRandom.current().nextDouble(RADIUS * 2) - RADIUS;
                    if (x * x + y * y <= RADIUS * RADIUS) {
                        circleCount++;
                    }
                }
                return circleCount;
            }, executorService);
        }

        for (int i = 0; i < CORES_COUNT; i++) {
            try {
                circleCounter += futures[i].get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(Character.MAX_VALUE, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        return MONTE_CARLO_MULTIPLIER * circleCounter / simulationsCount;
    }
}

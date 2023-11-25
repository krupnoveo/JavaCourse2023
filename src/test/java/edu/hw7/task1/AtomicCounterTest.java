package edu.hw7.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtomicCounterTest {
    @Test
    @DisplayName("Тест AtomicCounter.increment() и AtomicCounter.get()")
    public void increment_get_shouldWorkCorrectly() {
        int threadsCount = 10;
        ExecutorService service = Executors.newFixedThreadPool(threadsCount);
        AtomicCounter counter = new AtomicCounter();
        CompletableFuture<Void>[] futures = new CompletableFuture[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            futures[i] = CompletableFuture.runAsync(() -> {
                for (int j = 0; j < 100000; j++) {
                    counter.increment();
                }
            }, service);
        }
        CompletableFuture.allOf(futures).join();
        assertEquals(1000000, counter.get());
    }
}

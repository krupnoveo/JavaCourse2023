package edu.hw9.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsCollectorTest {
    public static Stream<Arguments> genCorrectData() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new double[] {1, 3, 2, 10},
                    new double[] {5, 10, 4, 1},
                    new double[] {8, 9, 1, 2},
                    new double[] {6, 12, 1, 5}
                ),
                Map.of(
                    "1", List.of(1.0, 10.0, 16.0, 4.0),
                    "2", List.of(1.0, 10.0, 20.0, 5.0),
                    "3", List.of(1.0, 9.0, 20.0, 5.0),
                    "4", List.of(1.0, 12.0, 24.0, 6.0)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("genCorrectData")
    @DisplayName("Тест StatsCollector все методы")
    public void allMethods_shouldWorkCorrectly(List<double[]> data, Map<String, List<Double>> expected) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        StatsCollector collector = new StatsCollector();
        for (int i = 0; i < 4; i++) {
            int temp = i;
            executorService.submit(() -> {
                collector.addMetric(String.valueOf(temp + 1), data.get(temp));
            });
        }
        ConcurrentMap<String, List<Double>> actual = collector.getCollectedStatsAfterAllFinished();
        collector.close();
        executorService.shutdown();
        assertEquals(expected, actual);
    }
}

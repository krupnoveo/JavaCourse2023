package edu.hw8.task2;

import edu.hw8.task2.Fibonacchi;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class FixedThreadPoolTest {
    @Test
    @DisplayName("Тест FixedThreadPool")
    public void allMethods_shouldWorkCorrectly() {
        List<Integer> numbers = List.of(9, 2, 6, 20, 10, 15, 13);
        List<Long> expected = List.of(34L, 1L, 8L, 6765L, 55L, 610L, 233L);
        List<Long> actual = new CopyOnWriteArrayList<>();
        try (ThreadPool pool = FixedThreadPool.create(4)) {
            pool.start();
            for (int i = 0; i < 7; i++) {
                int temp = i;
                pool.execute(() -> actual.add(Fibonacchi.fib(numbers.get(temp))));
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}

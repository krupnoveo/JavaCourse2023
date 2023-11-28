package edu.hw7.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelFactorialTest {
    @Test
    @DisplayName("Тест ParallelFactorial.countFactorial()")
    public void countFactorial_shouldWorkCorrectly() {
        BigInteger actual = ParallelFactorial.countFactorial(10);
        assertEquals(BigInteger.valueOf(3628800), actual);
    }
}

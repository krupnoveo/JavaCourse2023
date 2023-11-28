package edu.hw7.task4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PiCalculatorTest {
    @Test
    @DisplayName("Тест PiCalculator.singleThreadCalculator()")
    public void singleThreadCalculator_shouldWorkCorrectly() {
        assertThat(PiCalculator.singleThreadCalculator(1000000000)).isCloseTo(Math.PI, Offset.offset(0.001));
    }
    @Test
    @DisplayName("Тест PiCalculator.multiThreadCalculator()")
    public void multiThreadCalculator_shouldWorkCorrectly() {
        assertThat(PiCalculator.multiThreadCalculator(1000000000)).isCloseTo(Math.PI, Offset.offset(0.001));
    }
}

package edu.hw1;

import edu.hw1.task6.KaprekarConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaprekarConstantTest {
    @DisplayName("Тест KaprekarConstant.stepsToKaprekarConstant с корректными входными данными")
    @ParameterizedTest(name = "{index} - for number {0} {1} steps are needed to achieve KaprekarConstant")
    @CsvSource({
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "1112, 5"
    })
    public void stepsToKaprekarConstant_shouldReturnCorrectAnswer(int num, int expected) {
        int actual = KaprekarConstant.stepsToKaprekarConstant(num, 0);
        assertEquals(expected, actual);
    }
    @DisplayName("Тест KaprekarConstant.stepsToKaprekarConstant с некорректными входными данными")
    @ParameterizedTest(name = "{index} - for number {0} answer is -1 because of incorrect format")
    @ValueSource(ints = {1000, 10000, 3333})
    public void stepsToKaprekarConstant_shouldReturnMinusOne(int num) {
        int actual = KaprekarConstant.stepsToKaprekarConstant(num, 0);
        assertEquals(-1, actual);
    }
}

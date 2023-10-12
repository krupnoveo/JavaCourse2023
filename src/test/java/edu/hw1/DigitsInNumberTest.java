package edu.hw1;

import edu.hw1.task2.DigitsInNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitsInNumberTest {
    @DisplayName("Тест DigitsInNumber.countDigits(long)")
    @ParameterizedTest(name = "{index} - длина {0} = {1} - верно")
    @CsvSource({
        "123, 3",
        "-1234, 4",
        "0, 1",
    })
    public void countDigits_shouldReturnCorrectAnswer(long number, int expectedLength) {
        int actualLength = DigitsInNumber.countDigits(number);
        assertEquals(expectedLength, actualLength);
    }
}

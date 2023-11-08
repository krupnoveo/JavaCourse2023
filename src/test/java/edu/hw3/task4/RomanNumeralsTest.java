package edu.hw3.task4;

import edu.hw3.task4.RomanNumerals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanNumeralsTest {
    @ParameterizedTest(name = "{index} - для числа {0} получена римская запись {1}")
    @DisplayName("Тест RomanNumerals.convertDecToRoman() с корректными входными данными")
    @CsvSource(value = {
        "10, X",
        "2, II",
        "101, CI",
        "3999, MMMCMXCIX"
    })
    public void convertDecToRoman_shouldReturnCorrectAnswer(int input, String expected) {
        assertEquals(expected, RomanNumerals.convertDecToRoman(input));
    }
    @ParameterizedTest
    @DisplayName("Тест RomanNumerals.convertDecToRoman() с некорректными входными данными")
    @ValueSource(ints = {0, 4000})
    public void convertDecToRoman_shouldThrowIllegalArgumentException(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            RomanNumerals.convertDecToRoman(input);
        });
    }
}

package edu.hw1;

import edu.hw1.task5.SpecialPalindrom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class SpecialPalindromTest {
    @DisplayName("Тест SpecialPalindrom.isPalindromeDescedant с корректными входными данными и ожидаемым результатом true")
    @ParameterizedTest(name = "{index} - {0} is palindrome or it has descedant which is a palindrome - true")
    @ValueSource(ints = {11211230, 13001120, 23336014, 11, 123, 23421})
    public void isPalindromeDescedant_shouldReturnTrue(int inputString) {
        boolean actual = SpecialPalindrom.isPalindromeDescedant(inputString);
        assertTrue(actual);
    }
    @DisplayName("Тест SpecialPalindrom.isPalindromeDescedant с корректными входными данными и ожидаемым результатом false")
    @ParameterizedTest(name = "{index} - {0} is palindrome or it has descedant which is a palindrome - false")
    @ValueSource(ints = {122, 1, 1123})
    public void isPalindromeDescedant_shouldReturnFalse(int inputString) {
        boolean actual = SpecialPalindrom.isPalindromeDescedant(inputString);
        assertFalse(actual);
    }
}

package edu.hw5.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordStrengthCheckerTest {
    @Test
    @DisplayName("Тест PasswordStrengthChecker.isPasswordStrong()")
    public void isPasswordStrong_shouldReturnCorrectAnswer() {
        assertTrue(PasswordStrengthChecker.isPasswordStrong("12@!"));
        assertFalse(PasswordStrengthChecker.isPasswordStrong("123/.f"));
    }
}

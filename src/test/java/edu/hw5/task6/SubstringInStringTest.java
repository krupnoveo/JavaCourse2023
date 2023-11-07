package edu.hw5.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubstringInStringTest {
    @Test
    @DisplayName("Тест SubstringInString.isSubstringInString()")
    public void isSubstringInString_shouldReturnCorrectAnswer() {
        assertTrue(SubstringInString.isSubstringInString("123", "jsjf123fj098"));
        assertTrue(SubstringInString.isSubstringInString("123", "123"));
        assertFalse(SubstringInString.isSubstringInString("123", "kjkj12kjk"));
    }
}

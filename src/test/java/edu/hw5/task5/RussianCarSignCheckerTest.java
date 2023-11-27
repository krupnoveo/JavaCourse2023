package edu.hw5.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RussianCarSignCheckerTest {
    @Test
    @DisplayName("Тест RussianCarSignChecker.isCarSignCorrect()")
    public void isCarSignCorrect_shouldReturnCorrectAnswer() {
        assertTrue(RussianCarSignChecker.isCarSignCorrect("А123ВВ341"));
        assertTrue(RussianCarSignChecker.isCarSignCorrect("В143ОО97"));
        assertFalse(RussianCarSignChecker.isCarSignCorrect("Ф123ЛЛ999"));
        assertFalse(RussianCarSignChecker.isCarSignCorrect("В12ВВ123"));
    }
}

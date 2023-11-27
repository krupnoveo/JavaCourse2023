package edu.hw5.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlphabetZeroOneCheckerTest {
    @Test
    @DisplayName("Тест AlphabetZeroOneChecker.isLengthMoreThanThreeAndThirdSymbolEqualsZero()")
    public void isLengthMoreThanThreeAndThirdSymbolEqualsZero_shouldReturnCorrectAnswer() {
        assertTrue(AlphabetZeroOneChecker.isLengthMoreThanThreeAndThirdSymbolEqualsZero("1101"));
        assertFalse(AlphabetZeroOneChecker.isLengthMoreThanThreeAndThirdSymbolEqualsZero("1111"));
        assertFalse(AlphabetZeroOneChecker.isLengthMoreThanThreeAndThirdSymbolEqualsZero("10"));
    }
    @Test
    @DisplayName("Тест AlphabetZeroOneChecker.startSymbolEqualsEnd()")
    public void startSymbolEqualsEnd_shouldReturnCorrectAnswer() {
        assertTrue(AlphabetZeroOneChecker.startSymbolEqualsEnd("10101011101001"));
        assertTrue(AlphabetZeroOneChecker.startSymbolEqualsEnd("1"));
        assertFalse(AlphabetZeroOneChecker.startSymbolEqualsEnd("101110"));
    }
    @Test
    @DisplayName("Тест AlphabetZeroOneChecker.isLengthMoreThanOneAndLessThan3()")
    public void isLengthMoreThanOneAndLessThan3_shouldReturnCorrectAnswer() {
        assertTrue(AlphabetZeroOneChecker.isLengthMoreThanOneAndLessThan3("1"));
        assertTrue(AlphabetZeroOneChecker.isLengthMoreThanOneAndLessThan3("111"));
        assertFalse(AlphabetZeroOneChecker.isLengthMoreThanOneAndLessThan3(""));
        assertFalse(AlphabetZeroOneChecker.isLengthMoreThanOneAndLessThan3("1111"));
    }
}

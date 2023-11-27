package edu.hw5.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusAlphabetZeroOneCheckerTest {
    @Test
    @DisplayName("Тест BonusAlphabetZeroOneChecker.isStringUnevenLength()")
    public void isStringUnevenLength_shouldReturnCorrectAnswer() {
        assertTrue(BonusAlphabetZeroOneChecker.isStringUnevenLength("1"));
        assertTrue(BonusAlphabetZeroOneChecker.isStringUnevenLength("111"));
        assertFalse(BonusAlphabetZeroOneChecker.isStringUnevenLength("11"));
        assertFalse(BonusAlphabetZeroOneChecker.isStringUnevenLength(""));
    }
    @Test
    @DisplayName("Тест BonusAlphabetZeroOneChecker.isStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength()")
    public void isStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength_shouldReturnCorrectAnswer() {
        assertTrue(BonusAlphabetZeroOneChecker.isStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength("10"));
        assertTrue(BonusAlphabetZeroOneChecker.isStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength("011"));
        assertFalse(BonusAlphabetZeroOneChecker.isStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength("101"));
        assertFalse(BonusAlphabetZeroOneChecker.isStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength("01"));
    }
    @Test
    @DisplayName("Тест BonusAlphabetZeroOneChecker.isCountZeroDivisibleThree()")
    public void isCountZeroDivisibleThree_shouldReturnCorrectAnswer() {
        assertTrue(BonusAlphabetZeroOneChecker.isCountZeroDivisibleThree("101110011"));
        assertFalse(BonusAlphabetZeroOneChecker.isCountZeroDivisibleThree("11100"));
    }
    @Test
    @DisplayName("Тест BonusAlphabetZeroOneChecker.isStringNotEqualsTo11or111()")
    public void isStringNotEqualsTo11or111_shouldReturnCorrectAnswer() {
        assertTrue(BonusAlphabetZeroOneChecker.isStringNotEqualsTo11or111("0110"));
        assertTrue(BonusAlphabetZeroOneChecker.isStringNotEqualsTo11or111("110111"));
        assertFalse(BonusAlphabetZeroOneChecker.isStringNotEqualsTo11or111("11"));
        assertFalse(BonusAlphabetZeroOneChecker.isStringNotEqualsTo11or111("111"));
    }
    @Test
    @DisplayName("Тест BonusAlphabetZeroOneChecker.isEveryUnevenSymbolEqualsOne()")
    public void isEveryUnevenSymbolEqualsOne_shouldReturnCorrectAnswer() {
        assertTrue(BonusAlphabetZeroOneChecker.isEveryUnevenSymbolEqualsOne("11101"));
        assertFalse(BonusAlphabetZeroOneChecker.isEveryUnevenSymbolEqualsOne("011"));
    }
    @Test
    @DisplayName("Тест BonusAlphabetZeroOneChecker.isMoreThanOrEqualToTwoZerosAndLessOrEqualToOneOnes()")
    public void isMoreThanOrEqualToTwoZerosAndLessOrEqualToOneOnes_shouldReturnCorrectAnswer() {
        assertTrue(BonusAlphabetZeroOneChecker.isMoreThanOrEqualToTwoZerosAndLessOrEqualToOneOnes("010"));
        assertTrue(BonusAlphabetZeroOneChecker.isMoreThanOrEqualToTwoZerosAndLessOrEqualToOneOnes("00"));
        assertFalse(BonusAlphabetZeroOneChecker.isMoreThanOrEqualToTwoZerosAndLessOrEqualToOneOnes("10"));
        assertFalse(BonusAlphabetZeroOneChecker.isMoreThanOrEqualToTwoZerosAndLessOrEqualToOneOnes("01100"));
    }
}

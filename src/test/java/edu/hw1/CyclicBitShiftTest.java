package edu.hw1;

import edu.hw1.task7.CyclicBitShift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CyclicBitShiftTest {
    @DisplayName("Тест CyclicBitShift.rotateLeft(int, int) с корректными входными данными")
    @ParameterizedTest(name = "{index} - for number {0} and shift {1} answer is {2} - correct")
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "8, 0, 8",
        "0, 2, 0"
    })
    public void rotateLeft_shouldReturnCorrectAnswer(int n, int shift, int expected) {
        int actual = CyclicBitShift.rotateLeft(n, shift);
        assertEquals(expected, actual);
    }
    @DisplayName("Тест CyclicBitShift.rotateRight(int, int) с корректными входными данными")
    @ParameterizedTest(name = "{index} - for number {0} and shift {1} answer is {2} - correct")
    @CsvSource({
        "8, 1, 4",
        "17, 2, 12",
        "8, 0, 8",
        "0, 2, 0"
    })
    public void rotateRight_shouldReturnCorrectAnswer(int n, int shift, int expected) {
        int actual = CyclicBitShift.rotateRight(n, shift);
        assertEquals(expected, actual);
    }
    @DisplayName("Тест CyclicBitShift.rotateLeft(int, int) с некорректными входными данными")
    @ParameterizedTest(name = "{index} - for number {0} and shift {1} can't calculate answer because of incorrect format")
    @CsvSource({
        "-10, 1",
        "17, -2"
    })
    public void rotateLeft_shouldReturnMinusOne(int n, int shift) {
        int actual = CyclicBitShift.rotateLeft(n, shift);
        assertEquals(-1, actual);
    }
    @DisplayName("Тест CyclicBitShift.rotateRight(int, int) с некорректными входными данными")
    @ParameterizedTest(name = "{index} - for number {0} and shift {1} can't calculate answer because of incorrect format")
    @CsvSource({
        "-10, 1",
        "17, -2"
    })
    public void rotateRight_shouldReturnMinusOne(int n, int shift) {
        int actual = CyclicBitShift.rotateRight(n, shift);
        assertEquals(-1, actual);
    }
}

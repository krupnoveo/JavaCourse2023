package edu.hw1;

import edu.hw1.task3.ArrayInArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayInArrayTest {
    static Stream<Arguments> genCorrectData() {
        return Stream.of(
            Arguments.of(new int[] {1,2,3,4}, new int[] {0,6}),
            Arguments.of(new int[] {3, 1}, new int[] {4, 0})
        );
    }
    static Stream<Arguments> genIncorrectDataWithNoException() {
        return Stream.of(
            Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}),
            Arguments.of(new int[] {1,2,3}, new int[] {}),
            Arguments.of(new int[] {}, new int[] {1,2,3}),
            Arguments.of(new int[] {}, new int[] {})
        );
    }
    static Stream<Arguments> genIncorrectDataWithException() {
        return Stream.of(
            Arguments.of(null, new int[] {1,2,3}),
            Arguments.of(null, new int[] {}),
            Arguments.of(new int[] {1,2,3}, null),
            Arguments.of(new int[] {}, null),
            Arguments.of(null, null)
        );
    }
    @DisplayName("Тест ArrayInArray.isNestable(int[], int[]) с результатом true")
    @ParameterizedTest(name = "{index} - array {0} is nestable in {1} - true")
    @MethodSource("genCorrectData")
    public void isNestable_shouldReturnTrue(int[] first, int[] second) {
        boolean actual = ArrayInArray.isNestable(first, second);
        assertTrue(actual);
    }
    @DisplayName("Тест ArrayInArray.isNestable(int[], int[]) с результатом false")
    @ParameterizedTest(name = "{index} - array {0} is nestable in {1} - false")
    @MethodSource("genIncorrectDataWithNoException")
    public void isNestable_shouldReturnFalse(int[] first, int[] second) {
        boolean actual = ArrayInArray.isNestable(first, second);
        assertFalse(actual);
    }
    @DisplayName("Тест ArrayInArray.isNestable(int[], int[]) с вызовом исключения")
    @ParameterizedTest(name = "{index} - array {0} is nestable in {1} - array should not be null")
    @MethodSource("genIncorrectDataWithException")
    public void isNestable_shouldThrowException(int[] first, int[] second) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            ArrayInArray.isNestable(first, second);
        }, "IllegalArgumentException was expected");
        assertEquals("Array must not be null", thrown.getMessage());
    }
}


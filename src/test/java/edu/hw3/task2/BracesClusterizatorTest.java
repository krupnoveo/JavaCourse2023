package edu.hw3.task2;

import edu.hw3.task2.BracesClusterizator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BracesClusterizatorTest {
    static Stream<Arguments> genCorrectDataWithAnswers() {
        return Stream.of(
            Arguments.of("()()()", Arrays.asList("()", "()", "()")),
            Arguments.of("((()))", Arrays.asList("((()))")),
            Arguments.of("((()))(())()()(()())", Arrays.asList("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", Arrays.asList("((())())", "(()(()()))")),
            Arguments.of(")(())())(()(()())(", Arrays.asList()),
            Arguments.of("", Arrays.asList())
        );
    }
    @ParameterizedTest
    @DisplayName("Тест BracesClusterizator.clusterize() с корректными входными данными")
    @MethodSource("genCorrectDataWithAnswers")
    public void clusterize_shouldReturnCorrectAnswer(String input, List<String> expected) {
        List<String> actual = BracesClusterizator.clusterize(input);
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Тест BracesClusterizator.clusterize() с некорректными символами в входных данных")
    public void clusterize_shouldThrowIllegalArgumentException() {
        String input = "[]{}kk";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            BracesClusterizator.clusterize(input);
        });
        assertEquals("Only round braces allowed", thrown.getMessage());
    }
    @ParameterizedTest
    @DisplayName("Тест BracesClusterizator.clusterize() с null на входе")
    @NullSource
    public void clusterize_shouldThrowIllegalArgumentException(String input) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            BracesClusterizator.clusterize(input);
        });
        assertEquals("Input string must not be null", thrown.getMessage());
    }

}

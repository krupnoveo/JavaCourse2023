package edu.hw3.task3;

import edu.hw3.task3.WordsFrequency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordsFrequencyTest {
    static Stream<Arguments> genCorrectDataWithAnswers() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("a", 2, "bb", 2)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2))
        );
    }
    @ParameterizedTest
    @DisplayName("Тест WordsFrequency.showFrequencyDictionary() с корректными входными данными")
    @MethodSource("genCorrectDataWithAnswers")
    public void showFrequencyDictionary_shouldReturnCorrectAnswer(List<Object> input, Map<Object, Integer> expected) {
        Map<Object, Integer> actual = WordsFrequency.showFrequencyDictionary(input);
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Тест WordsFrequency.showFrequencyDictionary() с корректными входными данными, которые содержат null в ячейках массива")
    public void showFrequencyDictionary_shouldReturnCorrectAnswer() {
        List<String> input = new ArrayList<>();
        Map<String, Integer> expected = new HashMap<>();
        expected.put(null, 1);
        expected.put("a", 2);
        input.add(null);
        input.add("a");
        input.add("a");
        Map<String, Integer> actual = WordsFrequency.showFrequencyDictionary(input);
        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @DisplayName("Тест WordsFrequency.showFrequencyDictionary() с null входными данными")
    @NullSource
    public void showFrequencyDictionary_shouldThrowIllegalArgumentException(List<Integer> input) {
        assertThrows(IllegalArgumentException.class, () -> {
            WordsFrequency.showFrequencyDictionary(input);
        });
    }
}

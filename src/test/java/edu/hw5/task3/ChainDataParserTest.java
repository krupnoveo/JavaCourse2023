package edu.hw5.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChainDataParserTest {
    static Stream<Arguments> genDataWithAnswers() {
        return Stream.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12, 2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3,1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(2020, 3, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.of(2023, 11, 8))),
            Arguments.of("today", Optional.of(LocalDate.of(2023, 11, 7))),
            Arguments.of("yesterday", Optional.of(LocalDate.of(2023, 11, 6))),
            Arguments.of("1 day ago", Optional.of(LocalDate.of(2023, 11, 6))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.of(2017,9,25)))
        );
    }
    @ParameterizedTest
    @DisplayName("Тест ChainDateParser.getParsedDate() с корректными входными данными")
    @MethodSource("genDataWithAnswers")
    public void getParsedData_shouldReturnCorrectAnswer(String input, Optional<LocalDate> expected) {
        assertEquals(expected, ChainDateParser.getParsedDate(input));
    }
}

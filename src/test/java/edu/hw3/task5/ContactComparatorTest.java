package edu.hw3.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactComparatorTest {
    static Stream<Arguments> genCorrectDataWithAnswers() {
        return Stream.of(
            Arguments.of("abcd", "abcd", 0),
            Arguments.of("abcd", "abce", -1),
            Arguments.of("abce", "abcd", 1)
        );
    }
    @ParameterizedTest
    @DisplayName("Тест ContactComparator.compare()")
    @MethodSource("genCorrectDataWithAnswers")
    public void compare_shouldReturnCorrectAnswer(String o1, String o2, int expected) {
        ContactComparator comparator = new ContactComparator();
        int actual = comparator.compare(Contact.getSeparatedName(o1), Contact.getSeparatedName(o2));
        assertEquals(expected, actual);
    }
}

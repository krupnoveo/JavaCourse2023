package edu.hw3.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.provider.NullSource;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactsSorterTest {
    static Stream<Arguments> genCorrectDataWithAnswers() {
        return Stream.of(
            Arguments.of(
                List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
                SortOrder.ASC,
                List.of(
                    Contact.getSeparatedName("Thomas Aquinas"),
                    Contact.getSeparatedName("Rene Descartes"),
                    Contact.getSeparatedName("David Hume"),
                    Contact.getSeparatedName("John Locke")
                )
            ),
            Arguments.of(
                List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
                SortOrder.DESC,
                List.of(
                    Contact.getSeparatedName("Thomas Aquinas"),
                    Contact.getSeparatedName("Rene Descartes"),
                    Contact.getSeparatedName("David Hume"),
                    Contact.getSeparatedName("John Locke")
                ).reversed()
            )
        );
    }
    @ParameterizedTest
    @DisplayName("Тест ContactsSorter.parseContacts() с корректными входными данными")
    @MethodSource("genCorrectDataWithAnswers")
    public void parseContacts_shouldReturnCorrectAnswer(List<String> contacts, SortOrder order, List<Contact> expected) {
        Assertions.assertThat(ContactsSorter.parseContacts(contacts, order)).containsAll(expected);
    }
    @ParameterizedTest
    @DisplayName("Тест ContactsSorter.parseContacts() с null входными данными")
    @NullSource
    public void parseContacts_shouldThrowIllegalArgumentException(List<String> contacts) {
        assertThrows(IllegalArgumentException.class, () -> {
            ContactsSorter.parseContacts(contacts, SortOrder.ASC);
        });
    }
    @ParameterizedTest
    @DisplayName("Тест ContactsSorter.parseContacts() с пустыми входными данными")
    @EmptySource
    public void parseContacts_shouldReturnEmptyList(List<String> contacts) {
        Assertions.assertThat(ContactsSorter.parseContacts(contacts, SortOrder.ASC)).isEmpty();
    }
}

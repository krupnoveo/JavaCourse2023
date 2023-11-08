package edu.hw3.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactTest {
    static Stream<Arguments> genCorrectDataWithAnswers() {
        return Stream.of(
            Arguments.of("Thomas", "Thomas", ""),
            Arguments.of("John Cena", "John", "Cena")
        );
    }
    @ParameterizedTest
    @DisplayName("Тест Contact.getSeparatedName() с корректными входными данными")
    @MethodSource("genCorrectDataWithAnswers")
    public void getSeparatedName_shouldReturnCorrectAnswer(String fullName, String expectedFirstName, String expectedLastName) {
        String actualFirstName = Contact.getSeparatedName(fullName).getFirstName();
        String actualLastName = Contact.getSeparatedName(fullName).getSecondName();
        assertEquals(expectedFirstName, actualFirstName);
        assertEquals(expectedLastName, actualLastName);
    }
    @ParameterizedTest
    @DisplayName("Тест Contact.getSeparatedName() с null на входных данных")
    @NullSource
    public void getSeparatedName_shouldThrowIllegalArgumentException_1(String fullName) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Contact.getSeparatedName(fullName);
        });
        assertEquals("Full name must not be null", thrown.getMessage());
    }
    @ParameterizedTest
    @DisplayName("Тест Contact.getSeparatedName() с некорректным количеством слов в имени")
    @ValueSource(strings = {"", "Thomas Shelby Ofigitelen"})
    public void getSeparatedName_shouldThrowIllegalArgumentException_2(String fullName) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Contact.getSeparatedName(fullName);
        });
        assertEquals("Full name must contain at least name and not more than name + surname", thrown.getMessage());
    }
}

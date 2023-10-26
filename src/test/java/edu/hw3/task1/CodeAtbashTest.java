package edu.hw3.task1;

import edu.hw3.task1.CodeAtbash;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CodeAtbashTest {
    @ParameterizedTest(name = "{index} - для слова {0} было получено закодированное слово {1} - верно")
    @DisplayName("Тест CodeAtbash.atBash() с непустыми строками на вход")
    @CsvSource({
        "hello, svool",
        "Hello world!, Svool dliow!"
    })
    public void atBash_shouldReturnCorrectAnswer(String input, String expected) {
        String actual = CodeAtbash.atBash(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("Тест CodeAtbash.atBash() c пустой строкой на вход")
    @EmptySource
    public void atBash_shouldReturnEmptyString(String input) {
        String actual = CodeAtbash.atBash(input);
        assertEquals("", actual);
    }

    @ParameterizedTest
    @DisplayName("Тест CodeAtbash.atBash() c null на вход")
    @NullSource
    public void atBash_shouldThrowIllegalArgumentException(String input) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CodeAtbash.atBash(input);
        });
        assertEquals("Input string must not be null", thrown.getMessage());
    }
}

package edu.hw1;

import edu.hw1.task4.BrokenString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;
public class BrokenStringTest {
    @DisplayName("Тест BrokenString.fixString(String) c правильными входными данными")
    @ParameterizedTest(name = "{index} - string {0} was changed to {1} - correct")
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
        "12, 21",
        "1, 1",
    })
    public void fixString_shouldReturnCorrectAnswer(String brokenString, String fixedString) {
        String actual = BrokenString.fixString(brokenString);
        assertEquals(fixedString, actual);
    }
    @DisplayName("Тест BrokenString.fixString(String) с пустой или null строкой на вход")
    @ParameterizedTest(name = "{index} - string {0} gives an empty string")
    @NullAndEmptySource
    public void fixString_shouldReturnEmptyAnswer(String brokenString) {
        String actual = BrokenString.fixString(brokenString);
        assertThat(actual).isBlank();
    }

}

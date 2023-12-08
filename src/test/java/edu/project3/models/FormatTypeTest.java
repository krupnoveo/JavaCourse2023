package edu.project3.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FormatTypeTest {
    @Test
    @DisplayName("Тест FormatType.getFormatByValue()")
    public void getFormatByValue_shouldReturnCorrectAnswer() {
        FormatType type = FormatType.getFormatByValue("adoc");
        assertEquals(type, FormatType.ADOC);
    }
    @Test
    @DisplayName("Тест FormatType.getFormatByValue() с некорректными входными данными")
    public void getFormatByValue_shouldThrowIllegalArgumentException_or_ReturnMarkdown() {
        assertThrows(IllegalArgumentException.class, () -> {
            FormatType.getFormatByValue("doc");
        });
        assertEquals(FormatType.MARKDOWN, FormatType.getFormatByValue(null));
    }
}

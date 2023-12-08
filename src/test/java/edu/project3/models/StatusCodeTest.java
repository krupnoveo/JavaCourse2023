package edu.project3.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatusCodeTest {
    @Test
    @DisplayName("Тест StatusCode.getStatusByValue()")
    public void getStatusByValue_shouldReturnCorrectAnswer() {
        StatusCode code = StatusCode.getStatusByValue(100);
        assertEquals(code, StatusCode.Continue);
    }
    @Test
    @DisplayName("Тест StatusCode.getStatusByValue() с некорректными входными данными")
    public void getStatusByValue_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StatusCode.getStatusByValue(-1);
        });
    }
}

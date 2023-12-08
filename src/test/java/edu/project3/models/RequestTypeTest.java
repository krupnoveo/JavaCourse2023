package edu.project3.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTypeTest {
    @Test
    @DisplayName("Тест RequestType.getRequestTypeByCommand()")
    public void getRequestTypeByCommand_shouldReturnCorrectAnswer() {
        RequestType type = RequestType.getRequestTypeByCommand("GET");
        assertEquals(type, RequestType.GET);
    }
    @Test
    @DisplayName("Тест RequestType.getRequestTypeByCommand() с некорректными входными данными")
    public void getRequestTypeByCommand_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            RequestType.getRequestTypeByCommand("GETTING");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            RequestType.getRequestTypeByCommand(null);
        });
    }
}

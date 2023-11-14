package edu.project3.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentTypeTest {
    @Test
    @DisplayName("Тест ArgumentType.getTypeByValue()")
    public void getTypeByValue_shouldReturnCorrectAnswer() {
        ArgumentType type = ArgumentType.getTypeByValue("--path");
        assertEquals(type, ArgumentType.PATH);
    }
    @Test
    @DisplayName("Тест ArgumentType.getTypeByValue() с некорректными входными данными")
    public void getTypeByValue_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArgumentType.getTypeByValue("-from");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ArgumentType.getTypeByValue(null);
        });
    }
}

package edu.hw1;

import edu.hw1.task1.VideoLength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoLengthTest {
    @Test
    @DisplayName("Тест VideoLength.videoLengthInSeconds(String) с верными входными данными")
    public void videoLengthInSeconds_shouldReturnCorrectAnswer() {
        int actual = VideoLength.videoLengthInSeconds("02:10");
        assertEquals(130, actual);
    }
    @ParameterizedTest(name = "{index} - {0} - неверные входные данные")
    @DisplayName("Тест VideoLength.videoLengthInSeconds(String) с неверными входными данными")
    @ValueSource(strings = {"1:02", "02:1", "3:1", "-10:02", "-12:-2", "02.1:03", "999:61", ""})
    public void videoLengthInSeconds_shouldReturnMinusOne_whenIncorrectFormat(String testValue) {
        int actual = VideoLength.videoLengthInSeconds(testValue);
        assertEquals(-1, actual);
    }
    @ParameterizedTest(name = "{index} - {0} - неверные входные данные")
    @DisplayName("Тест VideoLength.videoLengthInSeconds(String) с неверными входными данными")
    @NullSource
    public void videoLengthInSeconds_shouldReturnMinusOne_whenNullInput(String testValue) {
        int actual = VideoLength.videoLengthInSeconds(testValue);
        assertEquals(-1, actual);
    }
}

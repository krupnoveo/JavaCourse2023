package edu.hw2;

import edu.hw2.task4.CallingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallingInfoTest {
    @Test
    @DisplayName("Тест CallingInfo.callingInfo()")
    public void callingInfo_shouldReturnCorrectAnswer() {
        CallingInfo record = CallingInfo.callingInfo();
        assertEquals(record.className(), "edu.hw2.CallingInfoTest");
        assertEquals(record.methodName(), "callingInfo_shouldReturnCorrectAnswer");
    }
}

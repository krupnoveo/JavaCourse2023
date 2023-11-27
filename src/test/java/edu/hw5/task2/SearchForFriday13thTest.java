package edu.hw5.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchForFriday13thTest {
    @Test
    @DisplayName("Тест SearchForFriday13th.getAllFridays13thInYear()")
    public void getAllFridays13thInYear_shouldReturnCorrectAnswer() {
        List<LocalDate> expected = List.of(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13));
        assertEquals(expected, SearchForFriday13th.getAllFridays13thInYear(2024));
    }
    @Test
    @DisplayName("Тест SearchForFriday13th.getNextFriday13thClosestToEnteredDate()")
    public void getNextFriday13thClosestToEnteredDate_shouldReturnCorrectAnswer() {
        LocalDate expected = LocalDate.of(2024, 9, 13);
        LocalDate date = LocalDate.of(2024, 8, 1);
        assertEquals(expected, SearchForFriday13th.getNextFriday13thClosestToEnteredDate(date));
    }
}

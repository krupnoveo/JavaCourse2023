package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class SearchForFriday13th {
    private static final int SEARCHED_DAY = 13;
    private static final int MONTHS_IN_YEAR = 12;

    private SearchForFriday13th() {}

    public static List<LocalDate> getAllFridays13thInYear(int year) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate date = LocalDate.of(year, Month.JANUARY, SEARCHED_DAY);
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                dates.add(date);
            }
            date = date.plusMonths(1);
        }
        return dates;
    }

    public static LocalDate getNextFriday13thClosestToEnteredDate(LocalDate date) {
        LocalDate currentDate = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            if (currentDate.getDayOfMonth() != SEARCHED_DAY) {
                currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            } else {
                break;
            }
        }
        return currentDate;
    }
}

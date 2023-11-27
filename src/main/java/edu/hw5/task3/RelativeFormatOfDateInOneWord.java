package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class RelativeFormatOfDateInOneWord extends DateParser {
    @Override
    Optional<LocalDate> parseDate(String string) {
        LocalDate currentDate = LocalDate.now();
        return switch (string.toLowerCase()) {
            case "tomorrow" -> Optional.of(currentDate.plusDays(1));
            case "yesterday" -> Optional.of(currentDate.minusDays(1));
            case "today" -> Optional.of(currentDate);
            default -> next != null ? next.parseDate(string) : Optional.empty();
        };
    }
}

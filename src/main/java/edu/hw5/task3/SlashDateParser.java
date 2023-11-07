package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlashDateParser extends DateParser {
    private static final Pattern DATE_FORMAT = Pattern.compile("((\\d{1,2})/(\\d{1,2})/(\\d{2}|\\d{4}))");

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    Optional<LocalDate> parseDate(String string) {
        Matcher date = DATE_FORMAT.matcher(string);
        if (!date.matches()) {
            if (next != null) {
                return next.parseDate(string);
            } else {
                return Optional.empty();
            }
        }
        int year = Integer.parseInt(date.group(4));
        if (date.group(4).length() == 2) {
            int currentCentury = LocalDate.now().getYear() / 100;
            year = currentCentury * 100 + Integer.parseInt(date.group(4));
        }
        return Optional.of(
            LocalDate.of(
                year,
                Integer.parseInt(date.group(3)),
                Integer.parseInt(date.group(2))
            )
        );
    }
}

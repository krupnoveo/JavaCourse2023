package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashDateParser extends DateParser {
    private static final Pattern DATE_FORMAT = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{1,2})");

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
        return Optional.of(
            LocalDate.of(
                Integer.parseInt(date.group(1)),
                Integer.parseInt(date.group(2)),
                Integer.parseInt(date.group(3))
            )
        );
    }
}

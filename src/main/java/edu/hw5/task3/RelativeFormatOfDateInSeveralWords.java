package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelativeFormatOfDateInSeveralWords extends DateParser {
    private static final Pattern DATE_FORMAT = Pattern.compile("(1 day ago)|(\\d+ days ago)");

    @Override
    Optional<LocalDate> parseDate(String string) {
        Matcher date = DATE_FORMAT.matcher(string);
        LocalDate currentDate = LocalDate.now();
        if (!date.matches()) {
            if (next != null) {
                return next.parseDate(string);
            } else {
                return Optional.empty();
            }
        }
        return Optional.of(currentDate.minusDays(Integer.parseInt(date.group(0).split(" ")[0])));
    }
}

package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AnaliticsForComputerClub {
    private static final Pattern CHECKER_FOR_INPUT_DATA = Pattern.compile(
        "([0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3[01]), ([01][0-9]|2[0-3]):[0-5][0-9]) - "
            + "([0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3[01]), ([01][0-9]|2[0-3]):[0-5][0-9])");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private static final short LEFT_HALF_OF_MATCHED_DATETIME = 1;
    private static final short RIGHT_HALF_OF_MATCHED_DATETIME = 5;

    private AnaliticsForComputerClub() {}

    public static Duration calculateAverageDurationOfSession(List<String> timesOfVisits) {
        Duration summurizedDuration = Duration.ZERO;
        for (String timeOfVisit : timesOfVisits) {
            summurizedDuration = summurizedDuration.plus(calculateDurationOfOneSession(timeOfVisit));
        }
        summurizedDuration = summurizedDuration.dividedBy(timesOfVisits.size());
        return summurizedDuration;
    }

    private static Duration calculateDurationOfOneSession(String timeOfVisit) {
        Matcher isTimeOfVisitCorrect = CHECKER_FOR_INPUT_DATA.matcher(timeOfVisit);
        if (!isTimeOfVisitCorrect.matches()) {
            throw new IllegalArgumentException("Incorrect format of input: " + timeOfVisit);
        }
        LocalDateTime startSessionTime = LocalDateTime.parse(
            isTimeOfVisitCorrect.group(LEFT_HALF_OF_MATCHED_DATETIME), DATE_TIME_FORMATTER);
        LocalDateTime endSessionTime = LocalDateTime.parse(
            isTimeOfVisitCorrect.group(RIGHT_HALF_OF_MATCHED_DATETIME), DATE_TIME_FORMATTER);
        String[] startAndEndOfSessionTime = timeOfVisit.split(" - ");
        if (!startSessionTime.toString().replace("T", ", ").equals(startAndEndOfSessionTime[0])
           || !endSessionTime.toString().replace("T", ", ").equals(startAndEndOfSessionTime[1])) {
            throw new IllegalArgumentException("Incorrect time or date in input data: " + timeOfVisit);
        }
        if (startSessionTime.isAfter(endSessionTime)) {
            throw new IllegalArgumentException("Start of session is after the end of it: " + timeOfVisit);
        }
        return Duration.between(startSessionTime, endSessionTime);
    }
}

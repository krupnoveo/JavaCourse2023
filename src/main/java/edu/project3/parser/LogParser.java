package edu.project3.parser;

import edu.project3.models.Log;
import edu.project3.models.Request;
import edu.project3.models.RequestType;
import edu.project3.models.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LogParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "(.*) - (.*) \\[(.*)\\] \"(.*) (.*) (.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\""
    );

    private LogParser() {}

    @SuppressWarnings("checkstyle:MagicNumber")
    public static Log parse(String log) {
        Matcher matcher = LOG_PATTERN.matcher(log);
        if (matcher.matches()) {
            return new Log(
                matcher.group(1),
                matcher.group(2),
                OffsetDateTime.parse(matcher.group(3), FORMATTER),
                new Request(
                    RequestType.getRequestTypeByCommand(matcher.group(4)),
                    matcher.group(5),
                    matcher.group(6),
                    matcher.group(10)
                ),
                new Response(
                    Integer.parseInt(matcher.group(7)),
                    Integer.parseInt(matcher.group(8))
                ),
                matcher.group(9)
            );
        }
        throw new IllegalArgumentException(
            "Read line can not be parsed"
        );
    }
}

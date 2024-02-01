package edu.project3.filter;

import edu.project3.models.Log;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateLogFilter implements LogFilter {
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateLogFilter(String from, String to) {
        if (from != null) {
            try {
                this.from = LocalDate.parse(from, FORMATTER).atStartOfDay().atOffset(ZoneOffset.UTC);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            this.from = OffsetDateTime.MIN;
        }
        if (to != null) {
            try {
                this.to = LocalDate.parse(to, FORMATTER).atStartOfDay().atOffset(ZoneOffset.UTC);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            this.to = OffsetDateTime.MAX;
        }
    }

    @Override
    public boolean isMatchingTheFilter(Log log) {
        return (log.localTime().isAfter(from) && log.localTime().isBefore(to));
    }
}

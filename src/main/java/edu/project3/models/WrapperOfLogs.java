package edu.project3.models;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record WrapperOfLogs(List<String> filePaths, OffsetDateTime from, OffsetDateTime to, List<Log> logs) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public WrapperOfLogs(List<String> filePaths, String from, String to, List<Log> logs) {
        this(
            filePaths,
            from == null ? null : LocalDate.parse(from, FORMATTER).atStartOfDay().atOffset(ZoneOffset.UTC),
            to == null ? null : LocalDate.parse(to, FORMATTER).atStartOfDay().atOffset(ZoneOffset.UTC),
            logs
        );
    }
}

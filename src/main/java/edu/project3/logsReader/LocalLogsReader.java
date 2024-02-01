package edu.project3.logsReader;

import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LocalLogsReader implements LogsReader {
    private static final String LOGS_DIRECTORY = "logs/";
    private final List<String> logs = new ArrayList<>();

    @Override
    public List<String> readLogs(String path) {
        try (Stream<Path> paths = Files.walk(Paths.get(LOGS_DIRECTORY), FileVisitOption.FOLLOW_LINKS)) {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + LOGS_DIRECTORY + path);
            paths
                .filter(Files::isRegularFile)
                .filter(Files::isReadable)
                .filter(pathMatcher::matches)
                .forEach(this::getLogsFromFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return logs;
    }

    private void getLogsFromFile(Path path) {
        try {
            logs.addAll(Files.readAllLines(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

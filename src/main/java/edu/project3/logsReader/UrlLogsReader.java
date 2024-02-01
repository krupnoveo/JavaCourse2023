package edu.project3.logsReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class UrlLogsReader implements LogsReader {
    @Override
    public List<String> readLogs(String url) {
        List<String> logs = new ArrayList<>();
        try {
            URI logSource = new URI(url);
            try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                    logSource.toURL().openStream()
                )
            )) {
                String log = bufferedReader.readLine();
                while (log != null) {
                    logs.add(log);
                    log = bufferedReader.readLine();
                }
                return logs;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

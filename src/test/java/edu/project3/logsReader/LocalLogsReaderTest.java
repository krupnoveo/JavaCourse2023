package edu.project3.logsReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalLogsReaderTest {
    @Test
    @DisplayName("Тест LocalLogsReader.readLogs()")
    public void readLogs_shouldReturnCorrectAnswer() {
        List<String> expected = List.of(
            "84.6.230.129 - - [14/Nov/2023:16:34:05 +0000] \"HEAD /reciprocal/logistical_motivating-project.gif HTTP/1.1\" 200 2084 \"-\" \"Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0\"",
            "1.234.120.30 - - [14/Nov/2023:16:34:05 +0000] \"GET /firmware/monitoring.htm HTTP/1.1\" 200 1882 \"-\" \"Opera/8.57 (Windows 98; en-US) Presto/2.10.205 Version/10.00\"",
            "103.204.37.127 - - [14/Nov/2023:16:34:05 +0000] \"GET /Graphical%20User%20Interface/high-level_tangible/scalable.hmtl HTTP/1.1\" 200 3045 \"-\" \"Mozilla/5.0 (Windows NT 6.2) AppleWebKit/5360 (KHTML, like Gecko) Chrome/38.0.858.0 Mobile Safari/5360\""
        );
        assertEquals(expected, new LocalLogsReader().readLogs("test/logs_test.txt"));
    }
    @Test
    @DisplayName("Тест LocalLogsReader.readLogs() с некорректным путём до файла")
    public void readLogs_shouldReturnEmptyList() {
        List<String> expected = List.of();
        assertEquals(expected, new LocalLogsReader().readLogs("logs_test.txt"));
    }
}

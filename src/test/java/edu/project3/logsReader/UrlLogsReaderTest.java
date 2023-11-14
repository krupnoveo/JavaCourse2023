package edu.project3.logsReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UrlLogsReaderTest {
    @Test
    @DisplayName("Тест UrlLogsReaderTest.readLogs()")
    public void readLogs_shouldReturnCorrectAnswer() {
        String first = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        String last = "79.136.114.202 - - [04/Jun/2015:07:06:35 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 334 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";
        List<String> actual = new UrlLogsReader().readLogs("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        assertEquals(51462, actual.size());
        assertEquals(first, actual.getFirst());
        assertEquals(last, actual.getLast());
    }
    @Test
    @DisplayName("Тест UrlLogsReaderTest.readLogs() с некорректной ссылкой на удалённый файл")
    public void readLogs_should() {
        assertThrows(RuntimeException.class, () -> {
            List<String> actual = new UrlLogsReader().readLogs("https://raw.githubusercontent.com/elastic");
        });
    }
}

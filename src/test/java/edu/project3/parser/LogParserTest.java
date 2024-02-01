package edu.project3.parser;

import edu.project3.models.Log;
import edu.project3.models.Request;
import edu.project3.models.RequestType;
import edu.project3.models.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogParserTest {
    @Test
    @DisplayName("Тест LogParser.parse()")
    public void parse_shouldReturnCorrectResult() {
        String rawLog = "84.6.230.129 - - [14/Nov/2023:16:34:05 +0000] \"HEAD /reciprocal/logistical_motivating-project.gif HTTP/1.1\" 200 2083 \"-\" \"Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0\"";
        Log parsedLog = new Log(
            "84.6.230.129",
            "-",
            OffsetDateTime.of(
                2023,
                11,
                14,
                16,
                34,
                5,
                0,
                ZoneOffset.UTC
            ),
            new Request(
                RequestType.HEAD,
                "/reciprocal/logistical_motivating-project.gif",
                "HTTP/1.1",
                "Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0"
            ),
            new Response(
                200,
                2083
            ),
            "-"
        );
        assertEquals(parsedLog, LogParser.parse(rawLog));
    }
    @Test
    @DisplayName("Тест LogParser.parse() с некорректной структурой лога на вход")
    public void parse_shouldThrowIllegalArgumentException() {
        String rawLog = "1/1/1/1 - - []";
        assertThrows(IllegalArgumentException.class, () -> {
            LogParser.parse(rawLog);
        });
    }
}

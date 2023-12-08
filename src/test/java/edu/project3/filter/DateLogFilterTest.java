package edu.project3.filter;

import edu.project3.models.Log;
import edu.project3.models.Request;
import edu.project3.models.RequestType;
import edu.project3.models.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateLogFilterTest {
    @Test
    @DisplayName("Тест DateLogFilter.isMatchingTheFilter()")
    public void isMatchingTheFilter_shouldReturnCorrectAnswer() {
        Log log = new Log(
            "",
            "",
            OffsetDateTime.of(2023, 11, 14, 0, 0, 0, 0, ZoneOffset.UTC),
            new Request(RequestType.GET, "", "", ""),
            new Response(100, 0),
            ""
        );
        assertTrue(new DateLogFilter("2023-11-13", "2023-11-15").isMatchingTheFilter(log));
        assertTrue(new DateLogFilter("2023-11-13", null).isMatchingTheFilter(log));
        assertTrue(new DateLogFilter(null, "2023-11-15").isMatchingTheFilter(log));
    }
}

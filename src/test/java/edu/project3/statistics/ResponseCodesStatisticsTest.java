package edu.project3.statistics;

import edu.project3.models.Log;
import edu.project3.models.Request;
import edu.project3.models.RequestType;
import edu.project3.models.Response;
import edu.project3.models.WrapperOfLogs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseCodesStatisticsTest {
    @Test
    @DisplayName("ResponseCodesStatistics.getData()")
    public void getData_shouldReturnCorrectResult() {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        List<String> codesValues = new ArrayList<>();
        List<String> codesNames = new ArrayList<>();
        List<String> codesCount = new ArrayList<>();
        table.put("Код", codesValues);
        table.put("Имя", codesNames);
        table.put("Количество", codesCount);
        data.put("Коды ответа", table);
        codesValues.add("100");
        codesValues.add("200");
        codesNames.add("Continue");
        codesNames.add("OK");
        codesCount.add("2");
        codesCount.add("1");
        WrapperOfLogs wrapper = new WrapperOfLogs(
            List.of("logs/logs1.txt", "logs/logs2.txt"),
            null,
            "2023-11-15",
            List.of(
                new Log(
                    "1.1.1.1",
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
                        "/github.com",
                        "HTTP/1.1",
                        "Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0"
                    ),
                    new Response(
                        100,
                        200
                    ),
                    "-"
                ),
                new Log(
                    "2.2.2.2",
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
                        "/github.com",
                        "HTTP/1.1",
                        "Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0"
                    ),
                    new Response(
                        200,
                        400
                    ),
                    "-"
                ),
                new Log(
                    "2.2.2.2",
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
                        "/yandex.ru",
                        "HTTP/1.1",
                        "Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0"
                    ),
                    new Response(
                        100,
                        400
                    ),
                    "-"
                )
            )
        );
        assertEquals(data, new ResponseCodesStatistics().getData(wrapper));
    }
}

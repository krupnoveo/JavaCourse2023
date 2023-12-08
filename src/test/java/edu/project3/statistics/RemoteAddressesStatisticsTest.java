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

public class RemoteAddressesStatisticsTest {
    @Test
    @DisplayName("RemoteAddressesStatistics.getData()")
    public void getData_shouldReturnCorrectResult() {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        data.put("Запрашиваемые адреса", table);
        List<String> addresses = new ArrayList<>();
        List<String> counts = new ArrayList<>();
        table.put("Адрес", addresses);
        table.put("Количество", counts);
        addresses.add("2.2.2.2");
        addresses.add("1.1.1.1");
        counts.add("2");
        counts.add("1");
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
                        "/reciprocal/logistical_motivating-project.gif",
                        "HTTP/1.1",
                        "Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0"
                    ),
                    new Response(
                        200,
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
                        "/reciprocal/logistical_motivating-project.gif",
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
                        "/reciprocal/logistical_motivating-project.gif",
                        "HTTP/1.1",
                        "Mozilla/5.0 (Windows NT 6.0; en-US; rv:1.9.0.20) Gecko/1995-06-01 Firefox/36.0"
                    ),
                    new Response(
                        200,
                        400
                    ),
                    "-"
                )
            )
        );
        assertEquals(data, new RemoteAddressesStatistics().getData(wrapper));
    }
}

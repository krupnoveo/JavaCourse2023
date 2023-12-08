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

public class GeneralDataStatisticsTest {
    @Test
    @DisplayName("Тест GeneralDataStatistics.getData()")
    public void getData_shouldReturnCorrectResult() {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        data.put("Общая информация", table);
        table.put(
            "Метрика", List.of(
                "Файл(-ы)", "Начальная дата", "Конечная дата", "Количество запросов", "Средний размер ответа"
            ));
        List<String> values = new ArrayList<>();
        table.put("Значение", values);
        values.add("'logs/logs1.txt''logs/logs2.txt'");
        values.add("-");
        values.add("15.11.2023");
        values.add("2");
        values.add("300.0b");
        WrapperOfLogs wrapper = new WrapperOfLogs(
            List.of("logs/logs1.txt", "logs/logs2.txt"),
            null,
            "2023-11-15",
            List.of(
                new Log(
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
                        200
                    ),
                    "-"
                ),
                new Log(
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
                        400
                    ),
                    "-"
                )
            )
        );
        assertEquals(data, new GeneralDataStatistics().getData(wrapper));
    }
}

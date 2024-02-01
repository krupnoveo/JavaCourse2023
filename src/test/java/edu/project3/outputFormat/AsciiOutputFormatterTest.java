package edu.project3.outputFormat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsciiOutputFormatterTest {
    @Test
    @DisplayName("Тест AsciiOutputFormatterTest.getFormattedOutput")
    public void getFormattedOutput_shouldReturnCorrectResult() {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        data.put("Запрашиваемые ресурсы", table);
        List<String> addresses = new ArrayList<>();
        List<String> counts = new ArrayList<>();
        table.put("Ресурс", addresses);
        table.put("Количество", counts);
        addresses.add("'/github.com'");
        addresses.add("'/yandex.ru'");
        counts.add("10");
        counts.add("5");
        String expected = """
            ==== Запрашиваемые ресурсы
            |========================
            |       Ресурс|Количество
            |'/github.com'|        10
            | '/yandex.ru'|         5""";
        assertEquals(expected, new AsciiOutputFormatter().getFormattedOutput(data));
    }
}

package edu.project3.statistics;

import edu.project3.models.Log;
import edu.project3.models.WrapperOfLogs;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneralDataStatistics implements LogStatistics {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public Map<String, Map<String, List<String>>> getData(WrapperOfLogs logs) {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        data.put("Общая информация", table);
        table.put(
            "Метрика", List.of(
            "Файл(-ы)", "Начальная дата", "Конечная дата", "Количество запросов", "Средний размер ответа"
        ));
        List<String> values = new ArrayList<>();
        table.put("Значение", values);
        values.add("'" + String.join("''", logs.filePaths()) + "'");
        if (logs.from() == null) {
            values.add("-");
        } else {
            values.add(FORMATTER.format(logs.from()));
        }
        if (logs.to() == null) {
            values.add("-");
        } else {
            values.add(FORMATTER.format(logs.to()));
        }
        values.add(String.valueOf(logs.logs().size()));
        values.add(String.valueOf(getAverageAnswerWeight(logs.logs())) + "b");
        return data;
    }

    private double getAverageAnswerWeight(List<Log> logs) {
        double sum = 0;
        for (Log log : logs) {
            sum += log.response().bytesSent();
        }
        return sum / (double) logs.size();
    }
}

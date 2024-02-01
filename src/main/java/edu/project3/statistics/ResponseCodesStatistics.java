package edu.project3.statistics;

import edu.project3.models.Log;
import edu.project3.models.StatusCode;
import edu.project3.models.WrapperOfLogs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseCodesStatistics implements LogStatistics {
    private static final int NUMBER_OF_SHOWN_SOURCES = 5;

    @Override
    public Map<String, Map<String, List<String>>> getData(WrapperOfLogs logs) {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        List<String> codesValues = new ArrayList<>();
        List<String> codesNames = new ArrayList<>();
        List<String> codesCount = new ArrayList<>();
        table.put("Код", codesValues);
        table.put("Имя", codesNames);
        table.put("Количество", codesCount);
        data.put("Коды ответа", table);
        Map<Integer, Integer> codeCount = new HashMap<>();
        for (Log log : logs.logs()) {
            int code = log.response().status();
            codeCount.compute(code, (key, val) -> (val == null) ? 1 : val + 1);
        }
        Map<Integer, Integer> sortedSourcesCount = sortMapByValue(codeCount);
        sortedSourcesCount
            .entrySet()
            .stream()
            .limit(NUMBER_OF_SHOWN_SOURCES)
            .forEach(set -> {
                codesValues.add(String.valueOf(set.getKey()));
                codesNames.add(StatusCode.getStatusByValue(set.getKey()).getMessage());
                codesCount.add(String.valueOf(set.getValue()));
            });

        return data;
    }

    private Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        list = list.reversed();
        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}

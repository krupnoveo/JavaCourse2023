package edu.project3.statistics;

import edu.project3.models.Log;
import edu.project3.models.WrapperOfLogs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RemoteAddressesStatistics implements LogStatistics {
    private static final int NUMBER_OF_SHOWN_SOURCES = 5;

    @Override
    public Map<String, Map<String, List<String>>> getData(WrapperOfLogs logs) {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        data.put("Запрашиваемые адреса", table);
        List<String> addresses = new ArrayList<>();
        List<String> counts = new ArrayList<>();
        table.put("Адрес", addresses);
        table.put("Количество", counts);
        Map<String, Integer> sourcesCount = new HashMap<>();
        for (Log log : logs.logs()) {
            String address = log.remoteAddress();
            sourcesCount.compute(address, (key, val) -> (val == null) ? 1 : val + 1);
        }
        Map<String, Integer> sortedSourcesCount = sortMapByValue(sourcesCount);
        sortedSourcesCount
            .entrySet()
            .stream()
            .limit(NUMBER_OF_SHOWN_SOURCES)
            .forEach(set -> {
                addresses.add(set.getKey());
                counts.add(String.valueOf(set.getValue()));
            });

        return data;
    }

    private Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        list = list.reversed();
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}


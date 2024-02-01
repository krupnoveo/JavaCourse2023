package edu.project3.statistics;

import edu.project3.models.Log;
import edu.project3.models.WrapperOfLogs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestedResourcesStatistics implements LogStatistics {
    private static final int NUMBER_OF_SHOWN_SOURCES = 5;

    @Override
    public Map<String, Map<String, List<String>>> getData(WrapperOfLogs logs) {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        Map<String, List<String>> table = new LinkedHashMap<>();
        data.put("Запрашиваемые ресурсы", table);
        List<String> resources = new ArrayList<>();
        List<String> counts = new ArrayList<>();
        table.put("Ресурс", resources);
        table.put("Количество", counts);
        Map<String, Integer> resourcesCount = new HashMap<>();
        for (Log log : logs.logs()) {
            String source = log.request().uri();
            source = source.substring(source.lastIndexOf('/'));
            resourcesCount.compute(source, (key, val) -> (val == null) ? 1 : val + 1);
        }
        Map<String, Integer> sortedSourcesCount = sortMapByValue(resourcesCount);
        sortedSourcesCount
            .entrySet()
            .stream()
            .limit(NUMBER_OF_SHOWN_SOURCES)
            .forEach(set -> {
                resources.add("'" + set.getKey() + "'");
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

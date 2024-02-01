package edu.project3.statistics;

import edu.project3.models.WrapperOfLogs;
import java.util.List;
import java.util.Map;

public interface LogStatistics {
    Map<String, Map<String, List<String>>> getData(WrapperOfLogs logs);
}

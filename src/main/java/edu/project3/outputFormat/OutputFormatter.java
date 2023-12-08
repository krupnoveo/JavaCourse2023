package edu.project3.outputFormat;

import java.util.List;
import java.util.Map;

public interface OutputFormatter {
    String getFormattedOutput(Map<String, Map<String, List<String>>> data);
}

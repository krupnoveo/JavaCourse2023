package edu.project3.outputFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AsciiOutputFormatter implements OutputFormatter {
    @Override
    public String getFormattedOutput(Map<String, Map<String, List<String>>> data) {
        StringBuilder formattedTable = new StringBuilder();
        formattedTable
            .append("==== ")
            .append(data.entrySet().stream().findFirst().get().getKey())
            .append("\n");
        Map<String, List<String>> rawTable = data.entrySet().stream().findFirst().get().getValue();
        List<String> headers = rawTable.keySet().stream().toList();
        List<List<String>> lines = rawTable.values().stream().toList();
        List<Integer> maxWidthForEachColumn = getMaxWidthForEachColumn(headers, lines);
        int summurizedWidth = maxWidthForEachColumn.stream().mapToInt(value -> value).sum();
        formattedTable
            .append("|")
            .append("=".repeat(summurizedWidth + headers.size() - 1))
            .append("\n");
        for (int i = 0; i < headers.size(); i++) {
            int columnWidth = maxWidthForEachColumn.get(i);
            String header = headers.get(i);
            formattedTable
                .append("|")
                .append(" ".repeat(columnWidth - header.length()))
                .append(headers.get(i));
        }
        formattedTable.append("\n");
        int rowsInColumn = lines.get(0).size();
        for (int row = 0; row < rowsInColumn; row++) {
            for (int column = 0; column < lines.size(); column++) {
                int columnWidth = maxWidthForEachColumn.get(column);
                String word = lines.get(column).get(row);
                formattedTable
                    .append("|")
                    .append(" ".repeat(columnWidth - word.length()))
                    .append(word);
            }
            formattedTable.append(row == rowsInColumn - 1 ? "" : "\n");
        }
        return formattedTable.toString();
    }

    private List<Integer> getMaxWidthForEachColumn(List<String> headers, List<List<String>> lines) {
        List<Integer> maxWidthForEachColumn = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            int maxWidth = 0;
            if (headers.get(i).length() > maxWidth) {
                maxWidth = headers.get(i).length();
            }
            for (int j = 0; j < lines.get(i).size(); j++) {
                if (lines.get(i).get(j).length() > maxWidth) {
                    maxWidth = lines.get(i).get(j).length();
                }
            }
            maxWidthForEachColumn.add(maxWidth);
        }
        return maxWidthForEachColumn;
    }
}

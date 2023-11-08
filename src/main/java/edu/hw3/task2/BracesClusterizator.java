package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public final class BracesClusterizator {
    private BracesClusterizator() {}

    public static List<String> clusterize(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string must not be null");
        }
        if (!input.matches("[()]*")) {
            throw new IllegalArgumentException("Only round braces allowed");
        }
        int openedBraces = 0;
        int closedBraces = 0;
        List<String> answer = new ArrayList<>();
        StringBuilder tempCluster = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (symbol == '(') {
                openedBraces++;
            }
            if (symbol == ')') {
                closedBraces++;
            }
            tempCluster.append(symbol);
            if (openedBraces == closedBraces) {
                answer.add(tempCluster.toString());
                tempCluster = new StringBuilder();
                openedBraces = 0;
                closedBraces = 0;
            }
            if (closedBraces > openedBraces) {
                return new ArrayList<>();
            }
        }
        if (openedBraces != closedBraces) {
            return new ArrayList<>();
        }
        return answer;
    }
}

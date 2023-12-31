package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class WordsFrequency {
    private WordsFrequency() {}

    public static <T> Map<T, Integer> showFrequencyDictionary(List<T> input) {
        if (input == null) {
            throw new IllegalArgumentException("Input list must not be null");
        }
        Map<T, Integer> frequencyDictionary = new HashMap<>();
        for (T elem : input) {
            frequencyDictionary.compute(elem, (key, val) -> val == null ? 1 : val + 1);
        }
        return frequencyDictionary;
    }
}

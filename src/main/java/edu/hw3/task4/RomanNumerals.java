package edu.hw3.task4;

import java.util.TreeMap;

@SuppressWarnings("MagicNumber")
public final class RomanNumerals {
    private RomanNumerals() {}

    private final static  TreeMap<Integer, String> ROMAN_NUMBERS = new TreeMap<>();

    static {
        ROMAN_NUMBERS.put(1000, "M");
        ROMAN_NUMBERS.put(900, "CM");
        ROMAN_NUMBERS.put(500, "D");
        ROMAN_NUMBERS.put(400, "CD");
        ROMAN_NUMBERS.put(100, "C");
        ROMAN_NUMBERS.put(90, "XC");
        ROMAN_NUMBERS.put(50, "L");
        ROMAN_NUMBERS.put(40, "XL");
        ROMAN_NUMBERS.put(10, "X");
        ROMAN_NUMBERS.put(9, "IX");
        ROMAN_NUMBERS.put(5, "V");
        ROMAN_NUMBERS.put(4, "IV");
        ROMAN_NUMBERS.put(1, "I");
    }

    public static String convertDecToRoman(int input) {
        if (input < 1 || input > 3999) {
            throw new IllegalArgumentException("Input number must be between 1 and 3999 inclusively");
        }
        int tempNumber = input;
        StringBuilder answer = new StringBuilder();
        while (tempNumber > 0) {
            int maxVal = ROMAN_NUMBERS.floorKey(tempNumber);
            answer.append(ROMAN_NUMBERS.get(maxVal));
            tempNumber -= maxVal;
        }
        return answer.toString();
    }
}

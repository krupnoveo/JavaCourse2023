package edu.hw1.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class KaprekarConstant {
    private static final short KAPREKAR_CONSTANT = 6174;
    private static final short SMALLEST_ALLOWED_NUMBER = 1001;
    private static final short BIGGEST_ALLOWED_NUMBER = 9999;
    private static final short ALLOWED_NUMBER_OF_DIGITS = 4;

    private KaprekarConstant() {
    }

    public static int stepsToKaprekarConstant(int num, int count) {
        if ((num < SMALLEST_ALLOWED_NUMBER || num > BIGGEST_ALLOWED_NUMBER) && count == 0) {
            return -1;
        }
        if (num == KAPREKAR_CONSTANT) {
            return count;
        }
        ArrayList<String> sortedDigits = new ArrayList<>(Arrays.asList(Integer.toString(num).split("")));
        ArrayList<String> reverseSortedDigits = new ArrayList<>(Arrays.asList(Integer.toString(num).split("")));
        if (sortedDigits.size() < ALLOWED_NUMBER_OF_DIGITS) {
            sortedDigits.add("0");
            reverseSortedDigits.add("0");
        }
        Collections.sort(sortedDigits);
        Collections.sort(reverseSortedDigits);
        Collections.reverse(reverseSortedDigits);
        int smaller = Integer.parseInt(String.join("", sortedDigits));
        int bigger = Integer.parseInt(String.join("", reverseSortedDigits));
        int result = bigger - smaller;
        if (result == 0) {
            return -1;
        }
        return stepsToKaprekarConstant(result, count + 1);
    }
}

package edu.hw1.task2;

public final class DigitsInNumber {
    private final static short BASE_OF_SYSTEM_NUMBER = 10;

    private DigitsInNumber() {
    }

    public static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }
        int counter = 0;
        long absOfNumber = Math.abs(number);
        while (absOfNumber > 0) {
            counter++;
            absOfNumber /= BASE_OF_SYSTEM_NUMBER;
        }
        return counter;
    }
}

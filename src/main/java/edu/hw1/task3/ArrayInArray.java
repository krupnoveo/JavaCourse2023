package edu.hw1.task3;

public final class ArrayInArray {
    private ArrayInArray() {
    }

    public static boolean isNestable(int[] first, int[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (first.length == 0 || second.length == 0) {
            return false;
        }
        int minElemInFirst = getMinElemInArray(first);
        int minElemInSecond = getMinElemInArray(second);
        int maxElemInFirst = getMaxElemInArray(first);
        int maxElemInSecond = getMaxElemInArray(second);
        return minElemInFirst > minElemInSecond && maxElemInFirst < maxElemInSecond;
    }

    private static int getMinElemInArray(int[] array) {
        int min = array[0];
        for (int elem : array) {
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }

    private static int getMaxElemInArray(int[] array) {
        int max = array[0];
        for (int elem : array) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }
}

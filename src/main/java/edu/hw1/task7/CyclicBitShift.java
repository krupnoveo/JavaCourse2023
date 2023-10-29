package edu.hw1.task7;

public final class CyclicBitShift {
    private CyclicBitShift() {
    }

    public static int rotateLeft(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        if (shift == 0) {
            return n;
        }
        String binaryNum = Integer.toBinaryString(n);
        StringBuilder binaryResult = new StringBuilder();
        for (int i = 0; i < binaryNum.length(); i++) {
            binaryResult.append(binaryNum.charAt((i + shift) % binaryNum.length()));
        }
        return Integer.parseInt(binaryResult.toString(), 2);
    }

    public static int rotateRight(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        if (shift == 0) {
            return n;
        }
        String binaryNum = Integer.toBinaryString(n);
        StringBuilder binaryResult = new StringBuilder();
        int formattedShift = shift % binaryNum.length();
        for (int i = 0; i < binaryNum.length(); i++) {
            binaryResult.append(binaryNum.charAt((binaryNum.length() - formattedShift + i) % binaryNum.length()));
        }
        return Integer.parseInt(binaryResult.toString(), 2);
    }
}

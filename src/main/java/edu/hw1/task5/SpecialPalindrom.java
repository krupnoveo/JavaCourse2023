package edu.hw1.task5;

public final class SpecialPalindrom {
    private final static short SYSTEM_BASE = 10;

    private SpecialPalindrom() {
    }

    public static boolean isPalindromeDescedant(int inputNumber) {
        if (inputNumber < SYSTEM_BASE) {
            return false;
        }
        String tempString = Integer.toString(inputNumber);
        int tempNumber;
        int firstDigit;
        int secondDigit;
        while (true) {
            boolean isPalindrome = true;
            for (int j = 0; j < tempString.length() / 2; j++) {
                if (tempString.charAt(j) != tempString.charAt(tempString.length() - 1 - j)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (!isPalindrome) {
                String newString = "";
                for (int i = 0; i < tempString.length(); i += 2) {
                    firstDigit = Integer.parseInt(Character.toString(tempString.charAt(i)));
                    if (i == tempString.length() - 1) {
                        newString += Integer.toString(firstDigit);
                    } else {
                        secondDigit = Integer.parseInt(Character.toString(tempString.charAt(i + 1)));
                        tempNumber = firstDigit + secondDigit;
                        newString += Integer.toString(tempNumber);
                    }
                }
                if (newString.length() < 2) {
                    break;
                }
                tempString = newString;
            } else {
                return true;
            }
        }
        return false;
    }
}

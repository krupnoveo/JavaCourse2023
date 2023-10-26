package edu.hw3.task1;

public final class CodeAtbash {
    private static final short ASCII_LOWER_BOUND_CAP_LETERS = 65;
    private static final short ASCII_HIGHER_BOUND_CAP_LETERS = 90;
    private static final short ASCII_LOWER_BOUND_LOWER_CASE = 97;
    private static final short ASCII_HIGHER_BOUND_LOWER_CASE = 122;

    private CodeAtbash() {}

    public static String atBash(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string must not be null");
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            char encodedLetter;
            if ((int) letter >= ASCII_LOWER_BOUND_CAP_LETERS && (int) letter <= ASCII_HIGHER_BOUND_CAP_LETERS) {
                encodedLetter = (char) (ASCII_HIGHER_BOUND_CAP_LETERS - ((int) letter - ASCII_LOWER_BOUND_CAP_LETERS));
            } else if ((int) letter >= ASCII_LOWER_BOUND_LOWER_CASE && (int) letter <= ASCII_HIGHER_BOUND_LOWER_CASE) {
                encodedLetter = (char) (ASCII_HIGHER_BOUND_LOWER_CASE - ((int) letter - ASCII_LOWER_BOUND_LOWER_CASE));
            } else {
                encodedLetter = letter;
            }
            answer.append(encodedLetter);
        }
        return answer.toString();
    }
}

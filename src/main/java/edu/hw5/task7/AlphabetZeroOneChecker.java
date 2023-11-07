package edu.hw5.task7;

public final class AlphabetZeroOneChecker {
    private AlphabetZeroOneChecker() {}

    public static boolean isLengthMoreThanThreeAndThirdSymbolEqualsZero(String input) {
        return input.matches("[01]{2}0.*");
    }

    public static boolean startSymbolEqualsEnd(String input) {
        return input.matches("0[01]*0|1[01]*1|[01]");
    }

    public static boolean isLengthMoreThanOneAndLessThan3(String input) {
        return input.matches("[01]{1,3}");
    }
}

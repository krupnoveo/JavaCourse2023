package edu.hw5.task8;

public final class BonusAlphabetZeroOneChecker {
    private BonusAlphabetZeroOneChecker() {}

    public static boolean isStringUnevenLength(String input) {
        return input.matches("[01]([01]{2})*");
    }

    public static boolean isStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength(String input) {
        return input.matches("1([01]([01]{2})*)|0([01]{2})*");
    }

    public static boolean isCountZeroDivisibleThree(String input) {
        return input.matches("(1*01*01*01*)*");
    }

    public static boolean isStringNotEqualsTo11or111(String input) {
        return input.matches("(?!11$|111$)[01]*");
    }

    public static boolean isEveryUnevenSymbolEqualsOne(String input) {
        return input.matches("(1[01])*1?");
    }

    public static boolean isMoreThanOrEqualToTwoZerosAndLessOrEqualToOneOnes(String input) {
        return input.matches("0{2,}1?|1?0{2,}|0+1?0+");
    }
}

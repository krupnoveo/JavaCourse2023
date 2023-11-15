package edu.hw5.task7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AlphabetZeroOneChecker {
    private final static Pattern FORMAT = Pattern.compile("([01]+)");

    private AlphabetZeroOneChecker() {}

    public static boolean isLengthMoreThanThreeAndThirdSymbolEqualsZero(String input) {
        return input.matches("[01]{2}0.*");
    }

    public static boolean startSymbolEqualsEnd(String input) {
        Matcher matcher = FORMAT.matcher(input);
        return matcher.matches() && matcher.group(1).codePointAt(0) == matcher.group(1).codePointAt(input.length() - 1);
    }

    public static boolean isLengthMoreThanOneAndLessThan3(String input) {
        return input.matches("[01]{1,3}");
    }
}

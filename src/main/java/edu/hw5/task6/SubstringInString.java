package edu.hw5.task6;

public final class SubstringInString {
    private SubstringInString() {}

    public static boolean isSubstringInString(String substring, String string) {
        return string.matches(".*" + substring + ".*");
    }
}

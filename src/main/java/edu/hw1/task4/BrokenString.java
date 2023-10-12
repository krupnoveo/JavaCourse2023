package edu.hw1.task4;


public final class BrokenString {
    private BrokenString() {
    }

    public static String fixString(String brokenString) {
        if (brokenString == null) {
            return "";
        }
        String fixedString = "";
        for (int i = 0; i < brokenString.length(); i += 2) {
            if (i == brokenString.length() - 1) {
                fixedString += brokenString.charAt(i);
            } else {
                fixedString += brokenString.charAt(i + 1) + Character.toString(brokenString.charAt(i));
            }
        }
        return fixedString;
    }
}

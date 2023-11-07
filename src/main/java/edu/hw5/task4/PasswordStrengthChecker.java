package edu.hw5.task4;

public final class PasswordStrengthChecker {
    private PasswordStrengthChecker() {}

    public static boolean isPasswordStrong(String password) {
        return password.matches(".*[~!@#$%^&*|].*");
    }
}

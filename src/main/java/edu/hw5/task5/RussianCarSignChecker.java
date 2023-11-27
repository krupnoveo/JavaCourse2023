package edu.hw5.task5;

public final class RussianCarSignChecker {
    private RussianCarSignChecker() {}

    public static boolean isCarSignCorrect(String sign) {
        return sign.matches("[УКЕНХВАРСМТО]\\d{3}[УКЕНХВАРСМТО]{2}\\d{2,3}");
    }
}

package edu.project4.models;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineTransformationCoeffiecients(
    double a,
    double b,
    double c,
    double d,
    double e,
    double f,
    Color color
) {

    private static final int MAX_COLOR_VALUE = 256;

    public static AffineTransformationCoeffiecients getRandomCoefficients() {
        while (true) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double c = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);
            double f = ThreadLocalRandom.current().nextDouble(-1, 1);
            if (a * a + d * d < 1
             && b * b + e * e < 1
             && a * a + b * b + d * d + e * e < 1 + ((a * e - b * d) * (a * e - b * d))
            ) {
                return new AffineTransformationCoeffiecients(a, b, c, d, e, f, generateColor());
            }
        }
    }

    private static Color generateColor() {
        int r = ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE);
        int g = ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE);
        int b = ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE);
        return new Color(r, g, b);
    }
}

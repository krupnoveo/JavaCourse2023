package edu.hw10.task1.parametersGenerator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class DoubleGenerator implements Generator<Double> {
    @Override
    public Double generate(Annotation[] annotations) {
        double minValue = Double.MIN_VALUE;
        double maxValue = Double.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min min) {
                minValue = (double) min.value();
            } else if (annotation instanceof Max max) {
                maxValue = (double) max.value();
            }
        }
        return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
    }
}

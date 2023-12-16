package edu.hw10.task1.parametersGenerator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ShortGenerator implements Generator<Short> {
    @Override
    public Short generate(Annotation[] annotations) {
        short minValue = Short.MIN_VALUE;
        short maxValue = Short.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min min) {
                minValue = (short) min.value();
            } else if (annotation instanceof Max max) {
                maxValue = (short) max.value();
            }
        }
        return (short) ThreadLocalRandom.current().nextInt(minValue, maxValue);
    }
}

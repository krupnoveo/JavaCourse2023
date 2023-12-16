package edu.hw10.task1.parametersGenerator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class LongGenerator implements Generator<Long> {
    @Override
    public Long generate(Annotation[] annotations) {
        long minValue = Long.MIN_VALUE;
        long maxValue = Long.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min min) {
                minValue = min.value();
            } else if (annotation instanceof Max max) {
                maxValue = max.value();
            }
        }
        return ThreadLocalRandom.current().nextLong(minValue, maxValue);
    }
}

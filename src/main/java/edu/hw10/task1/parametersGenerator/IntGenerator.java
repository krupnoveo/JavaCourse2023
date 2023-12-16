package edu.hw10.task1.parametersGenerator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class IntGenerator implements Generator<Integer> {

    @Override
    public Integer generate(Annotation[] annotations) {
        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min min) {
                minValue = (int) min.value();
            } else if (annotation instanceof Max max) {
                maxValue = (int) max.value();
            }
        }
        return ThreadLocalRandom.current().nextInt(minValue, maxValue);
    }
}

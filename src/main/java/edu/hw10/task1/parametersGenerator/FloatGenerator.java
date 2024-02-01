package edu.hw10.task1.parametersGenerator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class FloatGenerator implements Generator<Float> {
    @Override
    public Float generate(Annotation[] annotations) {
        float minValue = Float.MIN_VALUE;
        float maxValue = Float.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min min) {
                minValue = (float) min.value();
            } else if (annotation instanceof Max max) {
                maxValue = (float) max.value();
            }
        }
        return ThreadLocalRandom.current().nextFloat(minValue, maxValue);
    }
}

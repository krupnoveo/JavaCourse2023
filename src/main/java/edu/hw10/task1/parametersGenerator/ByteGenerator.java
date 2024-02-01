package edu.hw10.task1.parametersGenerator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ByteGenerator implements Generator<Byte> {
    @Override
    public Byte generate(Annotation[] annotations) {
        byte minValue = Byte.MIN_VALUE;
        byte maxValue = Byte.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min min) {
                minValue = (byte) min.value();
            } else if (annotation instanceof Max max) {
                maxValue = (byte) max.value();
            }
        }
        return (byte) ThreadLocalRandom.current().nextInt(minValue, maxValue);
    }
}

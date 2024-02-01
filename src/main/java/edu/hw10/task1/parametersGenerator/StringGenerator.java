package edu.hw10.task1.parametersGenerator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator implements Generator<String> {
    private static final long MIN_LENGTH = 1;
    private static final long DIFF = 10;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public String generate(Annotation[] annotations) {
        long minLength = MIN_LENGTH;
        long maxLength = Long.MAX_VALUE;
        boolean isMaxPresent = false;
        boolean isNotNull = false;
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                isNotNull = true;
            } else if (annotation instanceof Min min) {
                minLength = min.value();
            } else if (annotation instanceof Max max) {
                maxLength = max.value();
                isMaxPresent = true;
            }
        }
        if (!isNotNull && ThreadLocalRandom.current().nextBoolean()) {
            return null;
        }
        if (minLength < 0) {
            minLength = 0;
        }
        if (maxLength < 0) {
            maxLength = 0;
        }
        if (!isMaxPresent) {
            maxLength = minLength + DIFF;
        }
        if (maxLength < minLength) {
            throw new IllegalArgumentException("Max must not be less than min");
        }
        StringBuilder stringBuilder = new StringBuilder();
        long length = ThreadLocalRandom.current().nextLong(minLength, maxLength);
        for (long i = 0; i < length; i++) {
            stringBuilder.append(ALPHABET.charAt(ThreadLocalRandom.current().nextInt(0, ALPHABET.length())));
        }
        return stringBuilder.toString();
    }
}

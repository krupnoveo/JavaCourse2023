package edu.hw10.task1.parametersGenerator;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanGenerator implements Generator<Boolean> {
    @Override
    public Boolean generate(Annotation[] annotations) {
        return ThreadLocalRandom.current().nextBoolean();
    }
}

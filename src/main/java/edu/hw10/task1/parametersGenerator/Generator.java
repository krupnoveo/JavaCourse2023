package edu.hw10.task1.parametersGenerator;

import java.lang.annotation.Annotation;

public interface Generator<T> {
    T generate(Annotation[] annotations);
}

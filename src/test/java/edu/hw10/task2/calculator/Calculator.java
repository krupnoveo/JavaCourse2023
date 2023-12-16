package edu.hw10.task2.calculator;

import edu.hw10.task2.annotation.Cache;

public interface Calculator {
    @Cache(persist = false)
    int countWithInMemoryCaching(int a, int b);

    @Cache(persist = true)
    int countWithDiskCaching(int a, int b);
}

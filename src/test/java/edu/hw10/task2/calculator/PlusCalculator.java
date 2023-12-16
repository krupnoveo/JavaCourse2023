package edu.hw10.task2.calculator;

public class PlusCalculator implements Calculator {
    @Override
    public int countWithInMemoryCaching(int a, int b) {
        return a + b;
    }

    @Override
    public int countWithDiskCaching(int a, int b) {
        return a + b;
    }
}

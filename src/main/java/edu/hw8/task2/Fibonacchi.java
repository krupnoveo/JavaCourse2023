package edu.hw8.task2;

public final class Fibonacchi {
    private Fibonacchi() {}

    public static long fib(int number) {
        if (number == 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }
}

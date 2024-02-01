package edu.hw7.task2;


import java.math.BigInteger;
import java.util.stream.IntStream;

public final class ParallelFactorial {
    private ParallelFactorial() {}

    public static BigInteger countFactorial(int value) {
        if (value < 0) {
            return BigInteger.valueOf(-1);
        }
        if (value < 2) {
            return BigInteger.valueOf(1);
        }
        return IntStream.rangeClosed(1, value).parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .orElse(BigInteger.valueOf(-1));
    }
}

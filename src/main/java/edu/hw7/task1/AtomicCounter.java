package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger counter;

    public AtomicCounter() {
        counter = new AtomicInteger(0);
    }

    public AtomicCounter(int initialValue) {
        counter = new AtomicInteger(initialValue);
    }

    public int increment() {
        return counter.getAndIncrement();
    }

    public int get() {
        return counter.get();
    }
}

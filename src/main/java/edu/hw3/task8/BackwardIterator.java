package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private int cursor;
    List<T> collection;

    public BackwardIterator(List<T> collection) {
        this.collection = collection;
        cursor = collection.size();
    }

    @Override
    public boolean hasNext() {
        return cursor >= 1;
    }

    @Override
    public T next() {
        cursor--;
        return collection.get(cursor);
    }
}

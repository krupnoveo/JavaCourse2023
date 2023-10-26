package edu.hw3.task7;

import java.util.Comparator;

public class ComparatorForTreeMap<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    }
}

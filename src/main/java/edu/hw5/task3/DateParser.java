package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    protected DateParser next = null;

    public void setNext(DateParser next) {
        this.next = next;
    }

    abstract Optional<LocalDate> parseDate(String string);
}

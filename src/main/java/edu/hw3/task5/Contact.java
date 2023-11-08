package edu.hw3.task5;

import java.util.Objects;

public final class Contact {
    private final String firstName;
    private final String secondName;

    private Contact(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public static Contact getSeparatedName(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("Full name must not be null");
        }
        String exceptionMessage = "Full name must contain at least name and not more than name + surname";
        String[] name = fullName.split(" ");
        if (name.length == 1) {
            if (!name[0].isEmpty()) {
                return new Contact(name[0], "");
            }
            throw new IllegalArgumentException(exceptionMessage);
        } else if (name.length == 2) {
            return new Contact(name[0], name[1]);
        }
        throw new IllegalArgumentException(exceptionMessage);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFullName() {
        return firstName + " " + secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(secondName, contact.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }
}

package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ContactsSorter {
    private ContactsSorter() {}

    public static List<Contact> parseContacts(List<String> contacts, SortOrder sortOrder) {
        if (contacts == null) {
            throw new IllegalArgumentException("Contacts array must not be null");
        }
        if (contacts.isEmpty()) {
            return Collections.emptyList();
        }
        List<Contact> sortedContacts = new ArrayList<>();
        for (String fullName : contacts) {
            sortedContacts.add(Contact.getSeparatedName(fullName));
        }
        sortedContacts.sort(new ContactComparator());
        switch (sortOrder) {
            case ASC:
                break;
            case DESC:
                sortedContacts = sortedContacts.reversed();
                break;
            default:
                break;
        }
        return sortedContacts;
    }
}

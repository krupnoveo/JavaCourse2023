package edu.hw3.task5;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {

    @Override
    public int compare(Contact contact1, Contact contact2) {
        String contactName1 = contact1.getSecondName();
        String contactName2 = contact2.getSecondName();
        if (contactName1.isEmpty()) {
            contactName1 = contact1.getFirstName();
        }
        if (contactName2.isEmpty()) {
            contactName2 = contact2.getFirstName();
        }
        return contactName1.compareTo(contactName2);
    }
}

package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotThreadSafePersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> identificators = new HashMap<>();
    private final Map<String, List<Person>> names = new HashMap<>();
    private final Map<String, List<Person>> addresses = new HashMap<>();
    private final Map<String, List<Person>> phones = new HashMap<>();

    @Override
    public void add(Person person) {
        identificators.put(person.id(), person);
        names.computeIfAbsent(person.name(), key -> new ArrayList<>()).add(person);
        addresses.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person);
        phones.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person);
    }

    @Override
    public void delete(int id) {
        Person removedPerson = identificators.remove(id);
        if (removedPerson == null) {
            return;
        }
        names.get(removedPerson.name()).remove(removedPerson);
        addresses.get(removedPerson.address()).remove(removedPerson);
        phones.get(removedPerson.phoneNumber()).remove(removedPerson);
    }

    @Override
    public List<Person> findByName(String name) {
        return names.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return addresses.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return phones.get(phone);
    }
}

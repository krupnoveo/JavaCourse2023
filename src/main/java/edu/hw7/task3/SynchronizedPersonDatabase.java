package edu.hw7.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase extends NotThreadSafePersonDatabase {
    private final Map<Integer, Person> identificators = new HashMap<>();
    private final Map<String, List<Person>> names = new HashMap<>();
    private final Map<String, List<Person>> addresses = new HashMap<>();
    private final Map<String, List<Person>> phones = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        super.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return super.findByAddress(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return super.findByPhone(phone);
    }
}

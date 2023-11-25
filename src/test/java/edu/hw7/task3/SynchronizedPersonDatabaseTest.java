package edu.hw7.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedPersonDatabaseTest {
    @Test
    @DisplayName("Тест SynchronizedPersonDatabaseTest.add()/ .delete()/ .findByName()/ .findByAddress()/ .findByPhone()")
    public void allMethods_shouldWorkCorrectly() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();
        ExecutorService service = Executors.newFixedThreadPool(6);
        Person person1 = new Person(1, "Evgenij", "Moscow", "87777777777");
        Person person2 = new Person(2, "Petr", "Tambov", "87777772211");
        CompletableFuture<Void> addPerson1 = CompletableFuture.runAsync(() -> personDatabase.add(person1), service);
        CompletableFuture<Void> addPerson2 = CompletableFuture.runAsync(() -> personDatabase.add(person2), service);
        CompletableFuture<List<Person>> personByName = CompletableFuture.supplyAsync(() -> personDatabase.findByName("Evgenij"), service);
        CompletableFuture<Void> deletePerson2 = CompletableFuture.runAsync(() -> personDatabase.delete(2), service);
        CompletableFuture<List<Person>> personByAddress = CompletableFuture.supplyAsync(() -> personDatabase.findByAddress("Moscow"));
        CompletableFuture<List<Person>> personByPhone = CompletableFuture.supplyAsync(() -> personDatabase.findByPhone("87777777777"));

        CompletableFuture.allOf(addPerson1, addPerson2, personByName, personByAddress, personByPhone, deletePerson2).join();
        assertAll(
            () -> assertEquals(person1, personByName.get().get(0)),
            () -> assertEquals(person1, personByAddress.get().get(0)),
            () -> assertEquals(person1, personByPhone.get().get(0))
        );
    }
}

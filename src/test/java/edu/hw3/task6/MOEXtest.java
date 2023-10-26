package edu.hw3.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MOEXtest {
    @Test
    @DisplayName("Тест методов класса MOEX")
    public void moex_shouldReturnCorrectAnswer() {
        MOEX moex = new MOEX();
        moex.add(new Stock("tinkoff", 1000));
        moex.add(new Stock("sber", 900));
        moex.add(new Stock("yandex", 2000));
        moex.remove(new Stock("yandex", 2000));
        assertEquals(new Stock("tinkoff", 1000), moex.mostValuableStock());
    }
}

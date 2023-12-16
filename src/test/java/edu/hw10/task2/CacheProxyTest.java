package edu.hw10.task2;

import edu.hw10.task2.calculator.Calculator;
import edu.hw10.task2.calculator.PlusCalculator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheProxyTest {
    @TempDir
    private Path pathForDiskCaching;

    @SneakyThrows
    @Test
    @DisplayName("Тест CacheProxy.create() на классе с кэшированием в память")
    public void create_cacheImMemory_shouldWorkCorrectly() {
        Files.createDirectory(pathForDiskCaching.resolve("cache"));
        PlusCalculator calculator = new PlusCalculator();
        Calculator proxyCalculator = CacheProxy.create(calculator, PlusCalculator.class, pathForDiskCaching.resolve("cache"));
        int firstCall = proxyCalculator.countWithInMemoryCaching(1, 2);
        int secondCall = proxyCalculator.countWithInMemoryCaching(1, 2);
        assertEquals(firstCall, secondCall);
    }

    @SneakyThrows
    @Test
    @DisplayName("Тест CacheProxy.create() на классе с кэшированием на диск")
    public void create_cacheOnDisk_shouldWorkCorrectly() {
        Files.createDirectory(pathForDiskCaching.resolve("cache"));
        PlusCalculator calculator = new PlusCalculator();
        Calculator proxyCalculator = CacheProxy.create(calculator, PlusCalculator.class, pathForDiskCaching.resolve("cache"));
        int firstCall = proxyCalculator.countWithDiskCaching(1, 2);
        int secondCall = proxyCalculator.countWithDiskCaching(1, 2);
        assertAll(
            () -> assertEquals(firstCall, secondCall),
            () -> assertThat(pathForDiskCaching.resolve("cache").resolve("countWithDiskCaching.cache")).hasContent("[1,2]:3")
        );
    }
}

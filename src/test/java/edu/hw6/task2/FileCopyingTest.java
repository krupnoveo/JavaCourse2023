package edu.hw6.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileCopyingTest {
    @Test
    @DisplayName("Тест FileCopying.cloneFile()")
    public void cloneFile_shouldWorkCorrectly(@TempDir Path tempDir) {
        Path file = tempDir.resolve("test.txt");
        try {
            Files.createFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FileCopying.cloneFile(file);
        FileCopying.cloneFile(file);
        FileCopying.cloneFile(file);
        assertAll(
            () -> assertTrue(Files.exists(tempDir.resolve(Path.of("test.txt")))),
            () -> assertTrue(Files.exists(tempDir.resolve(Path.of("test - копия.txt")))),
            () -> assertTrue(Files.exists(tempDir.resolve(Path.of("test - копия (2).txt")))),
            () -> assertTrue(Files.exists(tempDir.resolve(Path.of("test - копия (3).txt"))))
        );
    }
}

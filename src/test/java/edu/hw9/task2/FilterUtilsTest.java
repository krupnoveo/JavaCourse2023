package edu.hw9.task2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterUtilsTest {
    @TempDir
    private static Path tempDir;

    @BeforeAll
    public static void setUpDirectory() {
        createNumberOfFilesInDirectories(tempDir.resolve("1"), ".txt", 10);
        createNumberOfFilesInDirectories(tempDir.resolve("2"), ".png", 13);
        createNumberOfFilesInDirectories(tempDir.resolve("3"), ".zip", 3);
        createNumberOfFilesInDirectories(tempDir.resolve("4"), ".txt", 13);
    }

    private static void createNumberOfFilesInDirectories(Path dir, String ext, int numberOfFilesInDir) {
        try {
            Files.createDirectories(dir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < numberOfFilesInDir; i++) {
            Path file = Path.of(i + ext);
            try {
                Files.createFile(dir.resolve(file));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (ext.equals(".txt")) {
                StringBuilder test = new StringBuilder();
                test.append("a".repeat(numberOfFilesInDir));
                try {
                    Files.writeString(dir.resolve(file), test.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    @Test
    @DisplayName("Тест FilterUtils.findDirectoriesWhichContainsMoreThanFiles()")
    public void findDirectoriesWhichContainsMoreThanFiles_shouldWorkCorrectly() {
        List<Path> rawResult = FiltersUtils.findDirectoriesWhichContainsMoreThanFiles(tempDir, 9);
        List<Path> actual = rawResult.stream().map(Path::getFileName).toList();
        List<Path> expected = List.of(Path.of("1"), Path.of("2"));
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Тест FilterUtils.findFilesWhichSatisfiesPredicate()")
    public void findFilesWhichSatisfiesPredicate_shouldWorkCorrectly() {
        List<Path> actual = FiltersUtils.findFilesWhichSatisfiesPredicate(tempDir, (a) -> {
            try {
                return a.toString().endsWith(".txt") && Files.size(a) > 12;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<Path> expected = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            expected.add(tempDir.resolve("4").resolve(Path.of(i + ".txt")));
        }
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}

package edu.hw6.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesFilterTest {
    @TempDir
    private Path tempDir;
    private List<Path> testFiles;

    @BeforeEach
    public void setUpFilesInTempDir() {
        testFiles = new ArrayList<>();
        try {
            testFiles.add(
                Files.writeString(
                    Files.createFile(tempDir.resolve("file1.txt")),
                        "test text"
                )
            );
            testFiles.add(
                Files.writeString(
                    Files.createFile(tempDir.resolve("file2.txt")),
                    "test text two"
                )
            );
            testFiles.add(
                Files.writeString(
                    Files.createFile(tempDir.resolve("file3.txt")),
                    "test text two three"
                )
            );
            testFiles.add(
                Files.write(
                    Files.createFile(tempDir.resolve("file4.png")),
                    new byte[] {(byte) 0x89, 'P', 'N', 'G'}
                )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Тест FilesFilter.largerThan()")
    public void largerThan_shouldWorkCorrectly() {
        List<Path> actual = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = FilesFilter.largerThan(14);
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(List.of(testFiles.get(2)), actual);
    }

    @Test
    @DisplayName("Тест FilesFilter.globMatches()")
    public void globMatches_shouldWorkCorrectly() {
        List<Path> actual = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = FilesFilter.globMatches("*.txt");
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrderElementsOf(testFiles.subList(0, 3));
    }

    @Test
    @DisplayName("Тест FilesFilter.regexContains()")
    public void regexContains_shouldWorkCorrectly() {
        List<Path> actual = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = FilesFilter.regexContains("file\\d");
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrderElementsOf(testFiles.subList(0, 4));
    }

    @Test
    @DisplayName("Тест FilesFilter.magicNumber()")
    public void magicNumber_shouldWorkCorrectly() {
        List<Path> actual = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = FilesFilter.magicNumber(0x89, 'P', 'N', 'G');
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactly(testFiles.get(3));
    }

    @Test
    @DisplayName("Тест FilesFilter.and()")
    public void and_shouldWorkCorrectly() {
        List<Path> actual = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = FilesFilter.largerThan(1).and(FilesFilter.globMatches("*.txt"));
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrderElementsOf(testFiles.subList(0, 3));
    }
}

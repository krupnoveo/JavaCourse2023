package edu.hw6.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

public class ChainOfFileStreamsTest {
    @Test
    @DisplayName("Тест ChainOfFileStreams.writeToFile()")
    public void writeToFile_shouldWorkCorrectly(@TempDir Path tempDir) {
        Path file = tempDir.resolve("test.txt");
        ChainOfFileStreams.writeToFile(file);
        assertThat(file).hasContent("Programming is learned by writing programs. ― Brian Kernighan");
    }
}

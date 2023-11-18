package edu.hw6.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DiskMapTest {
    @TempDir
    private Path tempDir;

    private DiskMap diskMap;

    @BeforeEach
    public void setUpDiskMap() {
        diskMap = new DiskMap(tempDir.resolve("tempFile.txt"));
    }

    @Test
    @DisplayName("Тест DiskMap.size()")
    public void size_shouldReturnCorrectAnswer() {
        diskMap.put("key", "value");
        assertEquals(1, diskMap.size());
    }

    @Test
    @DisplayName("Тест DiskMap.isEmpty()")
    public void isEmpty_shouldReturnCorrectAnswer() {
        assertTrue(diskMap.isEmpty());
        diskMap.put("key", "value");
        assertFalse(diskMap.isEmpty());
    }

    @Test
    @DisplayName("Тест DiskMap.containsKey()")
    public void containsKey_shouldReturnCorrectAnswer() {
        diskMap.put("key1", "value1");
        assertTrue(diskMap.containsKey("key1"));
        assertFalse(diskMap.containsKey("key2"));
    }

    @Test
    @DisplayName("Тест DiskMap.containsValue()")
    public void containsValue_shouldReturnCorrectAnswer() {
        diskMap.put("key", "value");
        assertTrue(diskMap.containsValue("value"));
        assertFalse(diskMap.containsValue("val"));
    }

    @Test
    @DisplayName("Тест DiskMap.get()")
    public void get_shouldReturnCorrectAnswer() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertEquals("value2", diskMap.get("key2"));
        assertNull(diskMap.get("key3"));
    }

    @Test
    @DisplayName("Тест DiskMap.put()")
    public void put_shouldWorkCorrectly() {
        String returned1 = diskMap.put("key", "value");
        String returned2 = diskMap.put("key", "val");
        assertThat(diskMap.getPath()).hasContent("key/val");
        assertNull(returned1);
        assertEquals("value", returned2);
    }

    @Test
    @DisplayName("Тест DiskMap.remove()")
    public void remove_shouldWorkCorrectly() {
        diskMap.put("key", "value");
        diskMap.put("key2", "value2");
        diskMap.remove("key");
        assertThat(diskMap.getPath()).hasContent("key2/value2");
    }

    @Test
    @DisplayName("Тест DiskMap.putAll()")
    public void putAll_shouldWorkCorrectly() {
        diskMap.put("key1", "val");
        diskMap.putAll(Map.of("key1", "value1", "key2", "value2"));
        assertThat(diskMap.getPath()).hasContent("key1/value1\nkey2/value2");
    }

    @Test
    @DisplayName("Тест DiskMap.clear()")
    public void clear_shouldWorkCorrectly() {
        diskMap.put("key", "value");
        diskMap.clear();
        assertTrue(diskMap.isEmpty());
    }

    @Test
    @DisplayName("Тест DiskMap.keySet()")
    public void keySet_shouldReturnCorrectAnswer() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertEquals(Set.of("key1", "key2"), diskMap.keySet());
    }

    @Test
    @DisplayName("Тест DiskMap.values()")
    public void values_shouldReturnCorrectAnswer() {
        diskMap.put("key1", "value");
        diskMap.put("key2", "value");
        assertEquals(List.of("value", "value"), diskMap.values());
    }

    @Test
    @DisplayName("Тест DiskMap.entrySet()")
    public void entrySet_shouldReturnCorrectAnswer() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertEquals(Set.of(Map.entry("key1", "value1"), Map.entry("key2", "value2")), diskMap.entrySet());
    }
}

package edu.hw6.task1;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static final String DELIMETR = "/";
    private final Path path;

    public Path getPath() {
        return path;
    }

    public DiskMap(Path path) {
        this.path = path;
        if (!Files.exists(this.path)) {
            try {
                Files.createFile(this.path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public String get(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("The received key must not be null");
        }
        Set<Entry<String, String>> entrySet = entrySet();
        for (Entry<String, String> entry : entrySet) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and/or value must not be null");
        }
        Entry<String, String> foundEntry = null;
        Set<Entry<String, String>> entrySet = entrySet();
        for (Entry<String, String> entry : entrySet) {
            if (entry.getKey().equals(key)) {
                if (entry.getValue().equals(value)) {
                    return value;
                }
                foundEntry = entry;
                var newSet = entrySet.stream()
                    .filter(en -> !en.getKey().equals(entry.getKey()))
                    .collect(Collectors.toSet());
                newSet.add(Map.entry(key, value));
                entrySet = newSet;
                break;
            }
        }
        if (foundEntry == null) {
            entrySet.add(Map.entry(key, value));
        }
        try {
            Files.write(
                path,
                entrySet.stream().map(entry -> entry.getKey() + DELIMETR + entry.getValue()).toList(),
                StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING
                );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (foundEntry != null) {
            return foundEntry.getValue();
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Incorrect argument for the key to be deleted: null");
        }
        String foundKey = null;
        Set<Entry<String, String>> entrySet = entrySet();
        for (Entry<String, String> entry : entrySet) {
            String entryKey = entry.getKey();
            if (entryKey.equals(key)) {
                foundKey = entryKey;
                break;
            }
        }
        if (foundKey != null) {
            try {
                Files.write(
                    path,
                    entrySet.stream()
                        .filter(entry -> !entry.getKey().equals(key))
                        .map(en -> en.getKey() + DELIMETR + en.getValue())
                        .toList(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return foundKey;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        Set<Entry<String, String>> entrySet = entrySet();
        Map<String, String> map = entrySet.stream()
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        map.putAll(m);
        try {
            Files.write(
                path,
                map.entrySet().stream()
                    .map(en -> en.getKey() + DELIMETR + en.getValue())
                    .toList(),
                StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE)) {
            channel.truncate(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return entrySet().stream()
            .map(Entry::getKey)
            .collect(Collectors.toSet());
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return entrySet().stream()
            .map(Entry::getValue)
            .toList();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> set = new HashSet<>();
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (!line.isEmpty()) {
                    String[] splittedLine = line.split(DELIMETR);
                    set.add(Map.entry(splittedLine[0], splittedLine[1]));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }
}

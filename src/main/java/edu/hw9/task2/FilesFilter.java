package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FilesFilter extends RecursiveTask<List<Path>> {
    private final Path startDir;
    private final Predicate<Path> predicate;

    public FilesFilter(Path startDir, Predicate<Path> predicate) {
        this.startDir = startDir;
        this.predicate = predicate;
    }

    @Override
    protected List<Path> compute() {
        List<RecursiveTask<List<Path>>> forks = new ArrayList<>();
        List<Path> foundFiles = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(startDir)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    if (predicate.test(path)) {
                        foundFiles.add(path);
                    }
                } else if (Files.isDirectory(path)) {
                    RecursiveTask<List<Path>> task = new FilesFilter(path, predicate);
                    task.fork();
                    forks.add(task);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (RecursiveTask<List<Path>> task : forks) {
            foundFiles.addAll(task.join());
        }
        return foundFiles;
    }
}

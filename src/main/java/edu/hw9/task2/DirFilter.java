package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirFilter extends RecursiveTask<List<Path>> {
    private final Path startDir;
    private final int minDirFilesCount;

    public DirFilter(Path startDir, int minDirFilesCount) {
        this.startDir = startDir;
        this.minDirFilesCount = minDirFilesCount;
    }

    @Override
    protected List<Path> compute() {
        List<RecursiveTask<List<Path>>> forks = new ArrayList<>();
        List<Path> foundDirs = new ArrayList<>();
        int filesInDirCounter = 0;
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(startDir)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    filesInDirCounter++;
                } else if (Files.isDirectory(path)) {
                    RecursiveTask<List<Path>> task = new DirFilter(path, minDirFilesCount);
                    task.fork();
                    forks.add(task);
                }
            }
            if (filesInDirCounter > minDirFilesCount) {
                foundDirs.add(startDir);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (RecursiveTask<List<Path>> task : forks) {
            foundDirs.addAll(task.join());
        }
        return foundDirs;
    }
}

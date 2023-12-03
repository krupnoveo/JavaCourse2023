package edu.hw9.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public final class FiltersUtils {
    private FiltersUtils() {}

    public static List<Path> findDirectoriesWhichContainsMoreThanFiles(Path startDir, int minFilesInDir) {
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            return forkJoinPool.invoke(new DirFilter(startDir, minFilesInDir));
        }
    }

    public static List<Path> findFilesWhichSatisfiesPredicate(Path startDir, Predicate<Path> predicate) {
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            return forkJoinPool.invoke(new FilesFilter(startDir, predicate));
        }
    }
}

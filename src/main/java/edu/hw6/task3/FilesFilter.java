package edu.hw6.task3;


import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public interface FilesFilter extends DirectoryStream.Filter<Path> {
    default FilesFilter and(FilesFilter filter) {
        return (path) -> accept(path) && filter.accept(path);
    }

    static FilesFilter largerThan(int size) {
        return (path) -> Files.size(path) > size;
    }

    static FilesFilter globMatches(String glob) {
        return (path) -> path.getFileSystem().getPathMatcher("glob:" + glob).matches(path.getFileName());
    }

    static FilesFilter regexContains(String regex) {
        return (path) -> Pattern.compile(regex).matcher(path.toString()).find();
    }

    static FilesFilter magicNumber(int... bytes) {
        return (path) -> {
            try (InputStream inputStream = Files.newInputStream(path)) {
                for (int byt : bytes) {
                    if (byt != inputStream.read()) {
                        return false;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        };
    }
}

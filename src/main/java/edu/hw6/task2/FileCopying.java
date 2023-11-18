package edu.hw6.task2;

import java.nio.file.Files;
import java.nio.file.Path;

public final class FileCopying {
    private static final String COPY_NAME = " - копия";

    private FileCopying() {}

    public static void cloneFile(Path path) {
        String fileDirectory = path.getParent().toString();
        String fileWithExt = path.getFileName().toString();
        String fileName = fileWithExt.substring(0, fileWithExt.indexOf("."));
        String fileExt = fileWithExt.substring(fileWithExt.indexOf("."));

        int copiesCount = 1;
        Path pathToCopy = Path.of(fileDirectory + "/" + fileName + COPY_NAME + fileExt);
        while (Files.exists(pathToCopy)) {
            copiesCount++;
            pathToCopy = Path.of(fileDirectory + "/" + fileName + COPY_NAME + " (" + copiesCount + ")" + fileExt);
        }
        try {
            Files.copy(path, pathToCopy);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public final class ChainOfFileStreams {
    private ChainOfFileStreams() {}

    public static void writeToFile(Path path) {
        try (OutputStream outputStream = Files.newOutputStream(path);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
        ) {
            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

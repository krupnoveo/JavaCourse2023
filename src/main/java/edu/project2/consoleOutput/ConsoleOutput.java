package edu.project2.consoleOutput;

public class ConsoleOutput implements Output {
    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void print(String text) {
        System.out.println(text);
    }
}

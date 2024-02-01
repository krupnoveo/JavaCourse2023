package edu.project3.output;

public class ConsoleOutputer implements Outputer {
    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void print(String text) {
        System.out.println(text + "\n");
    }
}

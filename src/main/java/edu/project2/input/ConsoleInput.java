package edu.project2.input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int input() {
        return scanner.nextInt();
    }
}

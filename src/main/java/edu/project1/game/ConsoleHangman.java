package edu.project1.game;

import edu.project1.outputInfo.GuessResult;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final Scanner sc;
    private static final Logger LOGGER = LogManager.getLogger();
    private final Session session;

    public ConsoleHangman(Session session) {
        this.session = session;
        this.sc = new Scanner(System.in);
    }

    public void run() {
        while (!session.getGameStatus().equals(GameStatus.STOPPED)) {
            char enteredLetter = inputFromUser();
            printState(tryGuess(enteredLetter));
        }
    }

    private GuessResult tryGuess(char input) {
        return session.guess(input);
    }

    private void printState(GuessResult guess) {
        String message = guess.message();
        LOGGER.info(message);
        if (!message.startsWith("You lost!")) {
            LOGGER.info("The word: " + new String(guess.state()));
        }
    }

    public char inputFromUser() {
        LOGGER.info("Guess a letter: ");
        while (true) {
            String input = sc.nextLine();
            if (input.length() != 1) {
                LOGGER.info("You should enter only one symbol! Try again");
            } else {
                return input.charAt(0);
            }
        }
    }
}

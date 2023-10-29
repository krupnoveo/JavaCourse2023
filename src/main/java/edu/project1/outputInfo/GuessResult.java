package edu.project1.outputInfo;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "You lost! The hidden word was <" + new String(state) + ">";
        }
    }

    record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "You won!";
        }
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "Hit!";
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "Missed, mistake " + attempt + " out of " + maxAttempts;
        }
    }

    record IncorrectInput(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "Incorrect symbol! You should enter symbols only from alphabet";
        }
    }

    record AlreadyUsedLetter(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "You have already used this letter! Try another one";
        }
    }
}

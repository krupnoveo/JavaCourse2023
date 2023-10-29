package edu.project1.game;

import edu.project1.outputInfo.GuessResult;
import edu.project1.settings.Settings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;


public class Session {
    private final String hiddenWord;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts = 0;
    private GameStatus gameStatus;

    private static final char STOP_WORD = '/';

    private static final int MIN_ALLOWED_LENGTH_OF_HIDDEN_WORD = 3;

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private final Map<Character, Boolean> usedLetters = new HashMap<>();

    public Session(Settings settings) {
        if (settings.getMaxAllowedAttempts() < 1) {
            throw new IllegalArgumentException("Number of maximum allowed attempts must be more than 0");
        }
        this.hiddenWord = settings.getWordsDictionary().randomWord();
        if (hiddenWord.length() < MIN_ALLOWED_LENGTH_OF_HIDDEN_WORD) {
            throw new IllegalArgumentException("Length of hidden word must be at least 3 letters");
        }
        this.maxAttempts = settings.getMaxAllowedAttempts();
        this.userAnswer = new char[hiddenWord.length()];
        Arrays.fill(userAnswer, '*');
        this.gameStatus = GameStatus.RUNNING;
        for (int i = 0; i < ALPHABET.length(); i++) {
            usedLetters.put(ALPHABET.charAt(i), false);
        }
    }


    @NotNull
    public GuessResult guess(char guess) {
        char userGuess = Character.toLowerCase(guess);
        GuessResult result;
        if (guess == STOP_WORD) {
            result = giveUp();
        } else {
            if (ALPHABET.indexOf(userGuess) == -1) {
                result = incorrectInput();
            } else {
                if (usedLetters.get(userGuess)) {
                    result = alreadyUsedLetter();
                } else {
                    usedLetters.put(userGuess, true);
                    if (hiddenWord.indexOf(userGuess) == -1) {
                        attempts++;
                        if (attempts - 1 == maxAttempts) {
                            return giveUp();
                        }
                        result = failedGuess();
                    } else {
                        for (int i = 0; i < hiddenWord.length(); i++) {
                            if (hiddenWord.charAt(i) == userGuess) {
                                userAnswer[i] = userGuess;
                            }
                        }
                        if (new String(userAnswer).equals(hiddenWord)) {
                            return win();
                        }
                        result = successfulGuess();
                    }
                }
            }
        }
        return result;
    }

    public int getAttempts() {
        return attempts;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    @NotNull
    public GuessResult failedGuess() {
        return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
    }

    @NotNull
    public GuessResult successfulGuess() {
        return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts);
    }

    @NotNull
    public GuessResult win() {
        gameStatus = GameStatus.STOPPED;
        return new GuessResult.Win(userAnswer, attempts, maxAttempts);
    }

    @NotNull
    public GuessResult giveUp() {
        gameStatus = GameStatus.STOPPED;
        return new GuessResult.Defeat(hiddenWord.toCharArray(), attempts, maxAttempts);
    }

    @NotNull
    public GuessResult incorrectInput() {
        return new GuessResult.IncorrectInput(userAnswer, attempts, maxAttempts);
    }

    @NotNull
    public GuessResult alreadyUsedLetter() {
        return new GuessResult.AlreadyUsedLetter(userAnswer, attempts, maxAttempts);
    }
}

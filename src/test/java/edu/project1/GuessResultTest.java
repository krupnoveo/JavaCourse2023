package edu.project1;

import edu.project1.outputInfo.GuessResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessResultTest {
    @Test
    @DisplayName("Тест результатов вывода GuessResult.Defeat()")
    public void defeat_shouldReturnCorrectAnswer() {
        char[] hiddenWord = "cat".toCharArray();
        GuessResult result = new GuessResult.Defeat(hiddenWord, 0, 3);
        assertEquals("You lost! The hidden word was <cat>", result.message());
    }
    @Test
    @DisplayName("Тест результатов вывода GuessResult.Win()")
    public void win_shouldReturnCorrectAnswer() {
        char[] hiddenWord = "cat".toCharArray();
        GuessResult result = new GuessResult.Win(hiddenWord, 0, 3);
        assertEquals("You won!", result.message());
    }
    @Test
    @DisplayName("Тест результатов вывода GuessResult.FailedGuess()")
    public void failedGuess_shouldReturnCorrectAnswer() {
        char[] hiddenWord = "cat".toCharArray();
        GuessResult result = new GuessResult.FailedGuess(hiddenWord, 2, 3);
        assertEquals("Missed, mistake 2 out of 3", result.message());
    }
    @Test
    @DisplayName("Тест результатов вывода GuessResult.SuccessfulGuess()")
    public void successfulGuess_shouldReturnCorrectAnswer() {
        char[] hiddenWord = "cat".toCharArray();
        GuessResult result = new GuessResult.SuccessfulGuess(hiddenWord, 2, 3);
        assertEquals("Hit!", result.message());
    }
    @Test
    @DisplayName("Тест результатов вывода GuessResult.IncorrectInput()")
    public void incorrectInput_shouldReturnCorrectAnswer() {
        char[] hiddenWord = "cat".toCharArray();
        GuessResult result = new GuessResult.IncorrectInput(hiddenWord, 2, 3);
        assertEquals("Incorrect symbol! You should enter symbols only from alphabet", result.message());
    }
    @Test
    @DisplayName("Тест результатов вывода GuessResult.AlreadyUsedLetter()")
    public void alreadyUsedLetter_shouldReturnCorrectAnswer() {
        char[] hiddenWord = "cat".toCharArray();
        GuessResult result = new GuessResult.AlreadyUsedLetter(hiddenWord, 2, 3);
        assertEquals("You have already used this letter! Try another one", result.message());
    }
}

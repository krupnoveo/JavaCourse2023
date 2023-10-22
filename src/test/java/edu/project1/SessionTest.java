package edu.project1;

import edu.project1.game.Session;
import edu.project1.outputInfo.GuessResult;
import edu.project1.settings.GameSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {
    @Test
    @DisplayName("Тест Session, в котором игра должна корректно завершить работу после исчерпания попыток")
    public void session_shouldReturnDefeat_whenUserUsedAllAttempts() {
        Session session = new Session(new GameSettings(2, new String[] {"java"}));
        session.guess('k');
        session.guess('l');
        GuessResult result = session.guess('m');
        assertEquals(GuessResult.Defeat.class, result.getClass());
    }
    @Test
    @DisplayName("Тест Session, в котором игра не должна засчитывать неудачную попытку, если такая буква уже была введена ранее")
    public void session_shouldReturnNotChangedNumberOfAttempts() {
        Session session = new Session(new GameSettings(2, new String[] {"java"}));
        session.guess('k');
        session.guess('k');
        session.guess('k');
        assertEquals(1, session.getAttempts());
    }
    @Test
    @DisplayName("Тест Session, в котором игра должна завершиться победой игрока после угадывания слова")
    public void session_shouldReturnWin_whenUserGuessedWord() {
        Session session = new Session(new GameSettings(2, new String[] {"cat"}));
        session.guess('c');
        session.guess('t');
        GuessResult result = session.guess('a');
        assertEquals(GuessResult.Win.class, result.getClass());
    }
    @Test
    @DisplayName("Тест Session, в котором игра должна завершиться поражением игрока после ввода символа прекращения игры")
    public void session_shouldReturnDefeat_whenUserEnteredStopWord() {
        Session session = new Session(new GameSettings(2, new String[] {"cat"}));
        GuessResult result = session.guess('/');
        assertEquals(GuessResult.Defeat.class, result.getClass());
    }
    @Test
    @DisplayName("Тест Session, в котором игра должна корректно изменить состояние после успешного угадывания буквы")
    public void session_shouldCorrectlyChangeItsState_whenUserSuccessfullyGuessedLetter() {
        Session session = new Session(new GameSettings(2, new String[] {"cat"}));
        GuessResult result = session.guess('a');
        assertEquals(0, session.getAttempts());
        assertEquals(GuessResult.SuccessfulGuess.class, result.getClass());
        assertEquals("*a*", new String(session.getUserAnswer()));
    }
    @Test
    @DisplayName("Тест Session, в котором игра должна корректно изменить состояние после неудачного угадывания буквы")
    public void session_shouldCorrectlyChangeItsState_whenUserUnsuccessfullyGuessedLetter() {
        Session session = new Session(new GameSettings(2, new String[] {"cat"}));
        GuessResult result = session.guess('d');
        assertEquals(1, session.getAttempts());
        assertEquals(GuessResult.FailedGuess.class, result.getClass());
        assertEquals("***", new String(session.getUserAnswer()));
    }
}

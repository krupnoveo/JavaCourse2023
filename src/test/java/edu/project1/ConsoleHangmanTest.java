package edu.project1;

import edu.project1.game.ConsoleHangman;
import edu.project1.game.Session;
import edu.project1.settings.GameSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ConsoleHangmanTest {
    @Test
    @DisplayName("Тест ConsoleHangman, в котором он должен вернуть исключение")
    public void consoleHangmanShouldThrowException_whenHiddenWordIsEmpty() {
        String[] words = new String[] {"ca", ""};
        assertThatThrownBy(() -> new ConsoleHangman(new Session(new GameSettings(words)))).isInstanceOf(
            IllegalArgumentException.class);
    }
    @Test
    @DisplayName("Тест ConsoleHangman, в котором он должен запуститься без исключений")
    public void gameShouldNotThrowExceptionAtStart_whenHiddenWordIsCorrect() {
        String[] words = new String[] {"cat", "water"};
        assertThatCode(() -> new ConsoleHangman(new Session(new GameSettings(words)))).doesNotThrowAnyException();
    }
}

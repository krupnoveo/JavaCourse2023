package edu.project1;

import edu.project1.settings.GameSettings;
import edu.project1.settings.Settings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GamesSettingsTest {
    @Test
    @DisplayName("Тест методов GameSettings")
    public void getMaxAllowedAttempts_and_getWordsDictionary_shouldReturnCorrectAnswer() {
        Settings settings1 = new GameSettings();
        Settings settings2 = new GameSettings(3);
        Settings settings3 = new GameSettings(new String[] {"cat", "dog"});
        Settings settings4 = new GameSettings(3, new String[] {"cat", "dog"});
        assertEquals(10, settings1.getMaxAllowedAttempts());
        assertArrayEquals(new String[]{"hangman", "car", "water"}, settings1.getWordsDictionary().getDefaultWords());
        assertEquals(3, settings2.getMaxAllowedAttempts());
        assertArrayEquals(new String[]{"hangman", "car", "water"}, settings2.getWordsDictionary().getDefaultWords());
        assertEquals(10, settings3.getMaxAllowedAttempts());
        assertArrayEquals(new String[] {"cat", "dog"}, settings3.getWordsDictionary().getDefaultWords());
        assertEquals(3, settings4.getMaxAllowedAttempts());
        assertArrayEquals(new String[] {"cat", "dog"}, settings4.getWordsDictionary().getDefaultWords());
    }
}

package edu.project1.settings;

import edu.project1.wordsDictionary.WordDictionary;

public class GameSettings implements Settings {
    private static final int DEFAULT_MAX_ALLOWED_ATTEMPTS = 10;
    private final int configuredMaxAllowedAttempts;
    private final WordDictionary wordDictionary;

    public GameSettings() {
        this.configuredMaxAllowedAttempts = DEFAULT_MAX_ALLOWED_ATTEMPTS;
        this.wordDictionary = new WordDictionary();
    }

    public GameSettings(int maxAllowedAttempts) {
        this.configuredMaxAllowedAttempts = maxAllowedAttempts;
        this.wordDictionary = new WordDictionary();
    }

    public GameSettings(String[] words) {
        this.configuredMaxAllowedAttempts = DEFAULT_MAX_ALLOWED_ATTEMPTS;
        this.wordDictionary = new WordDictionary(words);
    }

    public GameSettings(int maxAllowedAttempts, String[] words) {
        this.configuredMaxAllowedAttempts = maxAllowedAttempts;
        this.wordDictionary = new WordDictionary(words);
    }

    @Override
    public int getMaxAllowedAttempts() {
        return configuredMaxAllowedAttempts;
    }

    @Override
    public WordDictionary getWordsDictionary() {
        return wordDictionary;
    }
}

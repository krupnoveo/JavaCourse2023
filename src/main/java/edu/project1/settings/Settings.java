package edu.project1.settings;

import edu.project1.wordsDictionary.WordDictionary;

public interface Settings {
    int getMaxAllowedAttempts();

    WordDictionary getWordsDictionary();
}

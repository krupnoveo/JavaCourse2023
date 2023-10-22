package edu.project1.wordsDictionary;

import java.util.Random;
import org.jetbrains.annotations.NotNull;


public class WordDictionary implements Dictionary {
    private final String[] defaultWords;
    private static final int SIZE_OF_DEFAULT_WORDS_ARRAY = 3;

    public WordDictionary() {
        defaultWords = new String[SIZE_OF_DEFAULT_WORDS_ARRAY];
        defaultWords[0] = ("hangman");
        defaultWords[1] = ("car");
        defaultWords[2] = ("water");
    }

    public WordDictionary(String[] words) {
        this.defaultWords = words;
    }

    public String[] getDefaultWords() {
        return defaultWords;
    }

    @Override
    @NotNull
    public String randomWord() {
        Random random = new Random();
        int ind = random.nextInt(defaultWords.length);
        return defaultWords[ind];
    }
}

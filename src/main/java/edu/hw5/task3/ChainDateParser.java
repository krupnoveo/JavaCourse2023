package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public final class ChainDateParser {
    private static DashDateParser dashDateParser;
    private static RelativeFormatOfDateInOneWord relativeFormatOfDateInOneWord;
    private static RelativeFormatOfDateInSeveralWords relativeFormatOfDateInSeveralWords;
    private static SlashDateParser slashDateParser;

    private ChainDateParser() {}

    public static Optional<LocalDate> getParsedDate(String string) {
        dashDateParser = new DashDateParser();
        relativeFormatOfDateInOneWord = new RelativeFormatOfDateInOneWord();
        relativeFormatOfDateInSeveralWords = new RelativeFormatOfDateInSeveralWords();
        slashDateParser = new SlashDateParser();

        dashDateParser.setNext(slashDateParser);
        slashDateParser.setNext(relativeFormatOfDateInOneWord);
        relativeFormatOfDateInOneWord.setNext(relativeFormatOfDateInSeveralWords);

        return dashDateParser.parseDate(string);
    }
}

package edu.project3.inputReader;

import edu.project3.models.ArgumentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandLineReaderTest {
    static Stream<Arguments> genInputDataWithExpectedResult() {
        return Stream.of(
            Arguments.of(
                new String[] {"--path", "/logs1.txt", "--from", "2023-11-14", "--to", "2023-11-20", "--format", "markdown"},
                new HashMap<ArgumentType, String>() {{
                    put(ArgumentType.PATH, "/logs1.txt");
                    put(ArgumentType.FROM, "2023-11-14");
                    put(ArgumentType.TO, "2023-11-20");
                    put(ArgumentType.FORMAT, "markdown");
                }}
            ),
            Arguments.of(
                new String[] {"--path", "/logs1.txt", "--to", "2023-11-20", "--format", "markdown"},
                new HashMap<ArgumentType, String>() {{
                    put(ArgumentType.PATH, "/logs1.txt");
                    put(ArgumentType.FROM, null);
                    put(ArgumentType.TO, "2023-11-20");
                    put(ArgumentType.FORMAT, "markdown");
                }}
            ),
            Arguments.of(
                new String[] {"--path", "/logs1.txt", "--from", "2023-11-14", "--format", "markdown"},
                new HashMap<ArgumentType, String>() {{
                    put(ArgumentType.PATH, "/logs1.txt");
                    put(ArgumentType.FROM, "2023-11-14");
                    put(ArgumentType.TO, null);
                    put(ArgumentType.FORMAT, "markdown");
                }}
            ),
            Arguments.of(
                new String[] {"--path", "/logs1.txt", "--from", "2023-11-14"},
                new HashMap<ArgumentType, String>() {{
                    put(ArgumentType.PATH, "/logs1.txt");
                    put(ArgumentType.FROM, "2023-11-14");
                    put(ArgumentType.TO, null);
                    put(ArgumentType.FORMAT, null);
                }}
            ),
            Arguments.of(
                new String[] {"--path", "/logs1.txt"},
                new HashMap<ArgumentType, String>() {{
                    put(ArgumentType.PATH, "/logs1.txt");
                    put(ArgumentType.FROM, null);
                    put(ArgumentType.TO, null);
                    put(ArgumentType.FORMAT, null);
                }}
            )
        );
    }
    @ParameterizedTest
    @DisplayName("Тест CommandLineReader.getArguments() с корректными входными данными")
    @MethodSource("genInputDataWithExpectedResult")
    public void getArguments_shouldReturnCorrectAnswer_1(String[] arguments, Map<ArgumentType, String> expected) {
        assertEquals(expected, new CommandLineReader().getArguments(arguments));
    }
    @Test
    @DisplayName("Тест CommandLineReader.getArguments() с некорректными входными данными #1")
    public void getArguments_shouldThrowIllegalArgumentException_1() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineReader().getArguments(new String[] {"-path", "logs.txt"});
        });
        assertEquals(thrown.getMessage(), "Incorrect input! There must be --path at start with the path to file given after");
    }
    @Test
    @DisplayName("Тест CommandLineReader.getArguments() с некорректными входными данными #2")
    public void getArguments_shouldThrowIllegalArgumentException_2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineReader().getArguments(new String[] {"--path", "--from", "2023-11-14"});
        });
        assertEquals(thrown.getMessage(), "No argument(-s) for key --path");
    }
    @Test
    @DisplayName("Тест CommandLineReader.getArguments() с некорректными входными данными #3")
    public void getArguments_shouldThrowIllegalArgumentException_3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineReader().getArguments(new String[] {"--path", "logs.txt", "--from"});
        });
        assertEquals(thrown.getMessage(), "No argument(-s) for key --from");
    }
    @Test
    @DisplayName("Тест CommandLineReader.getArguments() с некорректными входными данными #4")
    public void getArguments_shouldThrowIllegalArgumentException_4() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineReader().getArguments(new String[] {"--path", "logs.txt", "--from", "--to"});
        });
        assertEquals(thrown.getMessage(), "Expected argument for --from, but got key instead");
    }

}

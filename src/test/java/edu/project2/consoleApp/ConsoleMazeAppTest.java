package edu.project2.consoleApp;

import edu.project2.consoleOutput.Message;
import edu.project2.consoleOutput.Output;
import edu.project2.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleMazeAppTest {
    private final List<String> output = new ArrayList<>();
    private final Queue<Integer> input = new LinkedList<>();
    private final Input reader = input::poll;
    private final Output printer = output::add;
    private ConsoleMazeApp app;

    @BeforeEach
    public void resetData() {
        output.clear();
        input.clear();
        app = new ConsoleMazeApp(reader, printer);
    }

    @Test
    @DisplayName("Тест ConsoleMazeApp.run() с корректными входными данными")
    public void run_shouldReturnCorrectData() {
        input.addAll(List.of(1, 11, 11, 1, 2, 2, 10, 10));
        app.run();
        assertThat(output.subList(0, 3)).containsExactly(
            new Message.Greeting().message(),
            new Message.ChooseGenerationAlgorithm().message(),
            new Message.InputDimensions().message()
        );
        assertThat(output.subList(4, 6)).containsExactly(
            new Message.ChooseSolver().message(),
            new Message.InputCoordinatesForPathSearch().message()
        );
    }
    @Test
    @DisplayName("Тест ConsoleMazeApp.run() с некорректным выбором алгоритма генерации лабиринта")
    public void run_shouldThrowIllegalArgumentException_1() {
        input.addAll(List.of(3, 11, 11, 1, 2, 2, 10, 10));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            app.run();
        });
        assertThat(thrown.getMessage()).isEqualTo("Incorrect variant of generation algorithm");
        assertThat(output).containsExactly(
            new Message.Greeting().message(),
            new Message.ChooseGenerationAlgorithm().message()
        );
    }
    @Test
    @DisplayName("Тест ConsoleMazeApp.run() с некорректным вводом размеров лабиринта")
    public void run_shouldThrowIllegalArgumentException_2() {
        input.addAll(List.of(1, 12, 11, 1, 2, 2, 10, 10));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            app.run();
        });
        assertThat(thrown.getMessage()).isEqualTo("Incorrect maze dimensions");
        assertThat(output).containsExactly(
            new Message.Greeting().message(),
            new Message.ChooseGenerationAlgorithm().message(),
            new Message.InputDimensions().message()
        );
    }
    @Test
    @DisplayName("Тест ConsoleMazeApp.run() с некорректным выбором алгоритма поиска пути")
    public void run_shouldThrowIllegalArgumentException_3() {
        input.addAll(List.of(1, 11, 11, 3, 2, 2, 10, 10));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            app.run();
        });
        assertThat(thrown.getMessage()).isEqualTo("Incorrect variant of solver algorithm");
        assertThat(output.subList(0, 3)).containsExactly(
            new Message.Greeting().message(),
            new Message.ChooseGenerationAlgorithm().message(),
            new Message.InputDimensions().message()
        );
        assertThat(output.subList(4, 5)).containsExactly(
            new Message.ChooseSolver().message()
        );
    }
    @Test
    @DisplayName("Тест ConsoleMazeApp.run() с некорректным вводом точек для поиска маршрута")
    public void run_shouldThrowIllegalArgumentException_4() {
        input.addAll(List.of(1, 11, 11, 1, 1, 2, 10, 10));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            app.run();
        });
        assertThat(thrown.getMessage()).isEqualTo("Incorrect coordinates of start and/or end cells");
        assertThat(output.subList(0, 3)).containsExactly(
            new Message.Greeting().message(),
            new Message.ChooseGenerationAlgorithm().message(),
            new Message.InputDimensions().message()
        );
        assertThat(output.subList(4, 6)).containsExactly(
            new Message.ChooseSolver().message(),
            new Message.InputCoordinatesForPathSearch().message()
        );
    }
}

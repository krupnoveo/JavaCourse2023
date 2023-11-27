package edu.project2.output;

import edu.project2.consoleOutput.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    @Test
    @DisplayName("Тест Message.Greeting().message")
    public void message_fromGreeting_shouldReturnCorrectSentence() {
        assertEquals("In this program you can generate mazes by using one of the available "
            + "algorithms and then get the path from one entered point to another", new Message.Greeting().message());
    }
    @Test
    @DisplayName("Тест Message.InputDimensions().message")
    public void message_fromInputDimensions_shouldReturnCorrectSentence() {
        assertEquals("Enter width and height of maze you want to generate splitting "
            + "them by space (sizes must be uneven numbers higher than 4): ", new Message.InputDimensions().message());
    }
    @Test
    @DisplayName("Тест Message.ChooseGenerationAlgorithm().message")
    public void message_fromChooseGenerationAlgorithm_shouldReturnCorrectSentence() {
        assertEquals("There are two available generation algorithms. "
            + "Enter 1 for DepthFirst algorithm, 2 - Prim algorithm: ", new Message.ChooseGenerationAlgorithm().message());
    }
    @Test
    @DisplayName("Тест Message.ChooseSolver().message")
    public void message_fromChooseSolver_shouldReturnCorrectSentence() {
        assertEquals("There are two available solver algorithms. "
            + "Enter 1 for DepthFirstSearch algorithm, 2 - BreadthFirstSearch: ", new Message.ChooseSolver().message());
    }
    @Test
    @DisplayName("Тест Message.InputCoordinatesForPathSearch().message")
    public void message_fromInputCoordinatesForPathSearch_shouldReturnCorrectSentence() {
        assertEquals("Enter coordinates for start (first two numbers) and "
            + "end (next two numbers) points, splitting them by space: ", new Message.InputCoordinatesForPathSearch().message());
    }
}

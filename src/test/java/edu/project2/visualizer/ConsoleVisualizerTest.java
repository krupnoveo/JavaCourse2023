package edu.project2.visualizer;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import edu.project2.visualizer.ConsoleVisualizer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleVisualizerTest {
    private final Cell[][] cells =  {
        {new Cell(0, 0, CellType.WALL), new Cell(0, 1, CellType.WALL), new Cell(0, 2, CellType.WALL), new Cell(0, 3, CellType.WALL), new Cell(0, 4, CellType.WALL)},
        {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.ROAD), new Cell(1, 2, CellType.ROAD), new Cell(1, 3, CellType.ROAD), new Cell(1, 4, CellType.WALL)},
        {new Cell(2, 0, CellType.WALL), new Cell(2, 1, CellType.WALL), new Cell(2, 2, CellType.WALL), new Cell(2, 3, CellType.ROAD), new Cell(2, 4, CellType.WALL)},
        {new Cell(3, 0, CellType.WALL), new Cell(3, 1, CellType.ROAD), new Cell(3, 2, CellType.ROAD), new Cell(3, 3, CellType.ROAD), new Cell(3, 4, CellType.WALL)},
        {new Cell(4, 0, CellType.WALL), new Cell(4, 1, CellType.WALL), new Cell(4, 2, CellType.WALL), new Cell(4, 3, CellType.WALL), new Cell(4, 4, CellType.WALL)}
    };
    @Test
    @DisplayName("Тест ConsoleVisualizer.visualizeGeneratedMaze()")
    public void visualizeGeneratedMaze_shouldReturnCorrectAnswer() {
        String expected = """
                ⬜⬜⬜⬜⬜
                ⬜⬛⬛⬛⬜
                ⬜⬜⬜⬛⬜
                ⬜⬛⬛⬛⬜
                ⬜⬜⬜⬜⬜
                """;
        assertEquals(expected, new ConsoleVisualizer().visualizeGeneratedMaze(new Maze(5, 5, cells)));
    }
    @Test
    @DisplayName("Тест ConsoleVisualizer.visualizeSolvedMaze()")
    public void visualizeSolvedMaze_shouldReturnCorrectAnswer() {
        String expected = """
                ⬜⬜⬜⬜⬜
                ⬜⭕🟩🟩⬜
                ⬜⬜⬜🟩⬜
                ⬜⬛⬛🏁⬜
                ⬜⬜⬜⬜⬜
                """;
        assertEquals(expected, new ConsoleVisualizer().visualizeSolvedMaze(new Maze(5, 5, cells), List.of(cells[1][1], cells[1][2], cells[1][3], cells[2][3], cells[3][3])));
    }
}

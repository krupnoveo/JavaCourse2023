package edu.hw9.task3;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParallelDFSTest {
    private final Cell[][] correctCells =  {
        {new Cell(0, 0, CellType.WALL), new Cell(0, 1, CellType.WALL), new Cell(0, 2, CellType.WALL), new Cell(0, 3, CellType.WALL), new Cell(0, 4, CellType.WALL)},
        {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.ROAD), new Cell(1, 2, CellType.ROAD), new Cell(1, 3, CellType.ROAD), new Cell(1, 4, CellType.WALL)},
        {new Cell(2, 0, CellType.WALL), new Cell(2, 1, CellType.WALL), new Cell(2, 2, CellType.WALL), new Cell(2, 3, CellType.ROAD), new Cell(2, 4, CellType.WALL)},
        {new Cell(3, 0, CellType.WALL), new Cell(3, 1, CellType.ROAD), new Cell(3, 2, CellType.ROAD), new Cell(3, 3, CellType.ROAD), new Cell(3, 4, CellType.WALL)},
        {new Cell(4, 0, CellType.WALL), new Cell(4, 1, CellType.WALL), new Cell(4, 2, CellType.WALL), new Cell(4, 3, CellType.WALL), new Cell(4, 4, CellType.WALL)}
    };
    private final Cell[][] incorrectCells =  {
        {new Cell(0, 0, CellType.WALL), new Cell(1, -1, CellType.WALL), new Cell(0, 2, CellType.WALL), new Cell(0, 3, CellType.WALL), new Cell(0, 4, CellType.WALL)},
        {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.ROAD), new Cell(1, 2, CellType.ROAD), new Cell(1, 3, CellType.ROAD), new Cell(1, 4, CellType.WALL)},
        {new Cell(2, 0, CellType.WALL), new Cell(2, 1, CellType.WALL), new Cell(2, 2, CellType.WALL), new Cell(2, 3, CellType.ROAD), new Cell(2, 4, CellType.WALL)},
        {new Cell(3, 0, CellType.WALL), new Cell(3, 1, CellType.ROAD), new Cell(3, 2, CellType.ROAD), new Cell(-3, 3, CellType.ROAD), new Cell(3, 4, CellType.WALL)},
        {new Cell(4, 0, CellType.WALL), new Cell(4, 1, CellType.WALL), new Cell(4, 2, CellType.WALL), new Cell(4, 3, CellType.WALL), new Cell(4, 4, CellType.WALL)}
    };
    @Test
    @DisplayName("Тест ParallelDFS.getPath() с корректными входными данными")
    public void getPath_fromParallelDFS_shouldReturnCorrectPath() {
        List<Cell>
            path = new ParallelDFS().getPath(new Maze(5, 5, correctCells), correctCells[1][1], correctCells[3][3]);
        List<Cell> expectedPath = List.of(correctCells[1][1], correctCells[1][2], correctCells[1][3], correctCells[2][3], correctCells[3][3]);
        assertEquals(expectedPath, path);
    }

    @Test
    @DisplayName("Тест ParallelDFS.getPath() с некорректными входными данными")
    public void getPath_fromParallelDFS_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ParallelDFS().getPath(new Maze(5, 5, incorrectCells), incorrectCells[0][1], incorrectCells[3][3]);
        });
    }
}

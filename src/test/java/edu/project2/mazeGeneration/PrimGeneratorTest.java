package edu.project2.mazeGeneration;

import edu.project2.mazeGeneration.PrimGenerator;
import edu.project2.mazeSolve.DepthFirstSearchSolver;
import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimGeneratorTest {
    @Test
    @DisplayName("Тест PrimGeneration.generate() с корректными входными данными")
    public void generate_fromPrimGenerator_shouldReturnCorrectMaze() {
        Maze maze = new PrimGenerator().generate(11, 11);
        Cell start = null;
        Cell end = null;
        for (int i = 0; i < maze.getHeight(); i++) {
            boolean found = false;
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getSurface()[i][j].getCellType().equals(CellType.ROAD)) {
                    start = maze.getSurface()[i][j];
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        for (int i = maze.getHeight() - 1; i >= 0; i--) {
            boolean found = false;
            for (int j = maze.getWidth() - 1; j >= 0; j--) {
                if (maze.getSurface()[i][j].getCellType().equals(CellType.ROAD)) {
                    end = maze.getSurface()[i][j];
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        List<Cell> path = new DepthFirstSearchSolver().getPath(maze, start, end);
        assertThat(path).isNotEmpty();
    }
    @ParameterizedTest
    @DisplayName("Тест PrimGeneration.generate() с некорректными входными данными")
    @ValueSource(ints = {2, 6})
    public void generate_fromPrimGenerator_shouldThrowIllegalArgumentException(int side) {
        assertThrows(IllegalArgumentException.class, () -> {
            new PrimGenerator().generate(side, side);
        });
    }
}

package edu.project2.mazeGeneration;

import edu.project2.mazeGeneration.DepthFirstGenerator;
import edu.project2.mazeSolve.DepthFirstSearchSolver;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepthFirstGeneratorTest {
    @Test
    @DisplayName("Тест DepthFirstGeneration.generate() с корректными входными данными")
    public void generate_fromDepthFirstGenerator_shouldReturnCorrectMaze() {
        Maze maze = new DepthFirstGenerator().generate(11, 11);
        List<Cell> path = new DepthFirstSearchSolver().getPath(maze, maze.getSurface()[1][1], maze.getSurface()[9][9]);
        assertThat(path).isNotEmpty();
    }
    @ParameterizedTest
    @DisplayName("Тест DepthFirstGeneration.generate() с некорректными входными данными")
    @ValueSource(ints = {2, 6})
    public void generate_fromDepthFirstGenerator_shouldThrowIllegalArgumentException(int side) {
        assertThrows(IllegalArgumentException.class, () -> {
            new DepthFirstGenerator().generate(side, side);
        });
    }
}

package edu.project2.mazeSolve;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Solver {
    List<Cell> getPath(Maze maze, Cell start, Cell end);
}

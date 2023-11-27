package edu.project2.visualizer;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Visualizer {
    String visualizeGeneratedMaze(Maze maze);

    String visualizeSolvedMaze(Maze maze, List<Cell> path);
}

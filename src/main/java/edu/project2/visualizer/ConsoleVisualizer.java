package edu.project2.visualizer;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import java.util.List;

public class ConsoleVisualizer implements Visualizer {
    private static final char ROAD_SYMBOL = '⬛';
    private static final char WALL_SYMBOL = '⬜';
    private static final String SOLVED_PATH_SYMBOL = "\uD83D\uDFE9";
    private static final char PATH_START_SYMBOL = '⭕';
    private static final String PATH_END_SYMBOL = "\uD83C\uDFC1";

    @Override
    public String visualizeGeneratedMaze(Maze maze) {
        StringBuilder answer = new StringBuilder();
        Cell[][] surface = maze.getSurface();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (surface[i][j].getCellType() == CellType.ROAD) {
                    answer.append(ROAD_SYMBOL);
                } else {
                    answer.append(WALL_SYMBOL);
                }
            }
            answer.append('\n');
        }
        return answer.toString();
    }

    @Override
    public String visualizeSolvedMaze(Maze maze, List<Cell> path) {
        StringBuilder answer = new StringBuilder();
        Cell[][] surface = maze.getSurface();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (path.contains(surface[i][j])) {
                    if (path.indexOf(surface[i][j]) == 0) {
                        answer.append(PATH_START_SYMBOL);
                    } else if (path.indexOf(surface[i][j]) == path.size() - 1) {
                        answer.append(PATH_END_SYMBOL);
                    } else {
                        answer.append(SOLVED_PATH_SYMBOL);
                    }
                } else {
                    if (surface[i][j].getCellType() == CellType.ROAD) {
                        answer.append(ROAD_SYMBOL);
                    } else {
                        answer.append(WALL_SYMBOL);
                    }
                }
            }
            answer.append('\n');
        }
        return answer.toString();
    }
}

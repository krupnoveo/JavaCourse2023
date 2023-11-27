package edu.project2.mazeSolve;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearchSolver implements Solver {
    private static final int[][] OFFSETS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private List<Cell> path;
    private boolean[][] visitedCells;

    private Maze maze;

    private Cell[][] parentCells;

    @Override
    public List<Cell> getPath(Maze maze, Cell start, Cell end) {
        if (checkInputData(maze, start, end)) {
            throw new IllegalArgumentException("Incorrect coordinates of start and/or end cells");
        }
        initializeSolver(maze);
        dfs(start, end);
        if (!visitedCells[end.getRow()][end.getColumn()]) {
            return Collections.emptyList();
        }
        path.add(end);
        Cell currentCell = parentCells[end.getRow()][end.getColumn()];
        while (!currentCell.equals(start)) {
            path.add(currentCell);
            currentCell = parentCells[currentCell.getRow()][currentCell.getColumn()];
        }
        path.add(start);
        return path.reversed();
    }

    private void dfs(Cell currentCell, Cell end) {
        List<Cell> neighbours = getNeighbours(currentCell.getRow(), currentCell.getColumn());
        visitedCells[currentCell.getRow()][currentCell.getColumn()] = true;
        if (visitedCells[end.getRow()][end.getColumn()]) {
            return;
        }
        for (Cell neighbour : neighbours) {
            if (!visitedCells[neighbour.getRow()][neighbour.getColumn()]) {
                parentCells[neighbour.getRow()][neighbour.getColumn()] = currentCell;
                dfs(neighbour, end);
            }
        }
    }

    private void initializeSolver(Maze maze) {
        path = new ArrayList<>();
        visitedCells = new boolean[maze.getHeight()][maze.getWidth()];
        this.maze = maze;
        this.parentCells = new Cell[maze.getHeight()][maze.getWidth()];
    }

    private List<Cell> getNeighbours(int row, int column) {
        List<Cell> neighbours = new ArrayList<>();
        for (int[] offset : OFFSETS) {
            int neighbourRow = row + offset[0];
            int neighbourColumn = column + offset[1];
            if (neighbourRow >= 0 && neighbourRow < maze.getHeight()
                && neighbourColumn >= 0 && neighbourColumn < maze.getWidth()
                && maze.getSurface()[neighbourRow][neighbourColumn].getCellType() == CellType.ROAD) {
                Cell neigbourCell = maze.getSurface()[neighbourRow][neighbourColumn];
                neighbours.add(neigbourCell);
            }
        }
        return neighbours;
    }

    private boolean checkInputData(Maze maze, Cell start, Cell end) {
        return (start.getRow() < 0 || start.getRow() >= maze.getHeight()
            || start.getColumn() < 0 || start.getColumn() >= maze.getWidth()
            || end.getRow() < 0 || end.getRow() >= maze.getHeight()
            || end.getColumn() < 0 || end.getColumn() >= maze.getWidth()
            || start.getCellType().equals(CellType.WALL)
            || end.getCellType().equals(CellType.WALL));
    }
}

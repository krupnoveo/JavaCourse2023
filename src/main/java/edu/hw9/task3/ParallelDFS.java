package edu.hw9.task3;

import edu.project2.mazeSolve.Solver;
import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelDFS implements Solver {
    private static final int[][] OFFSETS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private List<Cell> path;
    private CopyOnWriteArrayList<CopyOnWriteArrayList<Boolean>> visitedCells;
    private Maze maze;
    private CopyOnWriteArrayList<CopyOnWriteArrayList<Cell>> parentCells;

    @Override
    public List<Cell> getPath(Maze maze, Cell start, Cell end) {
        if (checkInputData(maze, start, end)) {
            throw new IllegalArgumentException("Incorrect coordinates of start and/or end cells");
        }
        initializeSolver(maze);
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            forkJoinPool.invoke(new DFS(start, end));
        }
        if (!visitedCells.get(end.getRow()).get(end.getColumn())) {
            return Collections.emptyList();
        }
        path.add(end);
        Cell currentCell = parentCells.get(end.getRow()).get(end.getColumn());
        while (!currentCell.equals(start)) {
            path.add(currentCell);
            currentCell = parentCells.get(currentCell.getRow()).get(currentCell.getColumn());
        }
        path.add(start);
        return path.reversed();
    }

    private void initializeSolver(Maze maze) {
        this.path = new ArrayList<>();
        this.visitedCells = new CopyOnWriteArrayList<>();
        for (int i = 0; i < maze.getHeight(); i++) {
            visitedCells.add(new CopyOnWriteArrayList<>());
            for (int j = 0; j < maze.getWidth(); j++) {
                visitedCells.get(i).add(false);
            }
        }
        this.maze = maze;
        this.parentCells = new CopyOnWriteArrayList<>();
        for (int i = 0; i < maze.getHeight(); i++) {
            parentCells.add(new CopyOnWriteArrayList<>());
            for (int j = 0; j < maze.getWidth(); j++) {
                parentCells.get(i).add(null);
            }
        }
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

    private class DFS extends RecursiveAction {
        private final Cell currentCell;
        private final Cell end;

        DFS(Cell currentCell, Cell end) {
            this.currentCell = currentCell;
            this.end = end;
        }

        @Override
        protected void compute() {
            List<Cell> neighbours = getNeighbours(currentCell.getRow(), currentCell.getColumn());
            List<RecursiveAction> forks = new ArrayList<>();
            visitedCells.get(currentCell.getRow()).set(currentCell.getColumn(), true);
            if (visitedCells.get(end.getRow()).get(end.getColumn())) {
                return;
            }
            for (Cell neighbour : neighbours) {
                if (!visitedCells.get(neighbour.getRow()).get(neighbour.getColumn())) {
                    parentCells.get(neighbour.getRow()).set(neighbour.getColumn(), currentCell);
                    RecursiveAction task = new DFS(neighbour, end);
                    task.fork();
                    forks.add(task);
                }
            }
            for (RecursiveAction task : forks) {
                task.join();
            }
        }
    }
}

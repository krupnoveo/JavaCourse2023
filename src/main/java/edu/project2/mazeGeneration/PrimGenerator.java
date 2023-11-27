package edu.project2.mazeGeneration;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGenerator implements MazeGenerator {
    private static final short MIN_ALLOWED_SIZE_OF_SIDE = 5;
    private int width;
    private int height;
    private Cell[][] surface;
    private boolean[][] visitedCells;
    private List<Cell> walls;

    private Random random;

    @Override
    public Maze generate(int width, int height) {
        if (!checkInputData(width, height)) {
            throw new IllegalArgumentException("Incorrect maze dimensions");
        }
        initializeGenerator(width, height);
        markAsMazePartAndAddWalls(random.nextInt(width), random.nextInt(height));
        Cell cell;
        while (!walls.isEmpty()) {
            int randomIndex = random.nextInt(walls.size());
            cell = walls.get(randomIndex);
            List<Cell> neighbourWalls = getHeighbourWalls(cell.getColumn(), cell.getRow());
            int countVisitedCells = 0;
            for (Cell neighbourWall : neighbourWalls) {
                if (visitedCells[neighbourWall.getRow()][neighbourWall.getColumn()]) {
                    countVisitedCells++;
                }
            }
            if (countVisitedCells == 1) {
                markAsMazePartAndAddWalls(cell.getColumn(), cell.getRow());
            }
            walls.remove(cell);
        }
        return new Maze(width, height, surface);
    }

    private void initializeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.visitedCells = new boolean[height][width];
        this.surface = getSettedSurface(width, height);
        this.walls = new ArrayList<>();
        this.random = new Random();
    }

    private Cell[][] getSettedSurface(int width, int height) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j, CellType.WALL);
            }
        }
        return cells;
    }

    private List<Cell> getHeighbourWalls(int column, int row) {
        List<Cell> neighbourWalls = new ArrayList<>();
        if (row - 1 >= 0) {
            neighbourWalls.add(surface[row - 1][column]);
        }
        if (row + 1 < height) {
            neighbourWalls.add(surface[row + 1][column]);
        }
        if (column - 1 >= 0) {
            neighbourWalls.add(surface[row][column - 1]);
        }
        if (column + 1 < width) {
            neighbourWalls.add(surface[row][column + 1]);
        }
        return neighbourWalls;
    }

    private void markAsMazePartAndAddWalls(int column, int row) {
        visitedCells[row][column] = true;
        surface[row][column].setCellType(CellType.ROAD);
        List<Cell> neighbourWalls = getHeighbourWalls(column, row);
        walls.addAll(neighbourWalls);
    }

    private boolean checkInputData(int width, int height) {
        return width >= MIN_ALLOWED_SIZE_OF_SIDE && height >= MIN_ALLOWED_SIZE_OF_SIDE
            && width % 2 != 0 && height % 2 != 0;
    }
}

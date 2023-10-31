package edu.project2.mazeGeneration;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DepthFirstGenerator implements MazeGenerator {
    private static final short MIN_ALLOWED_SIZE_OF_SIDE = 5;
    private int width;
    private int height;
    private Cell[][] surface;
    private boolean[][] visitedCells;
    private Stack<Cell> cellStack;

    private Random random;

    @Override
    public Maze generate(int width, int height) {
        if (!checkInputData(width, height)) {
            throw new IllegalArgumentException("Incorrect maze dimensions");
        }
        initializeGenerator(width, height);
        Cell currentCell = surface[1][1];
        visitedCells[1][1] = true;
        while (!allCellsVisited()) {
            List<Cell> notVisitedNeighbours = getNotVisitedNeighbours(currentCell.getColumn(), currentCell.getRow());
            if (!notVisitedNeighbours.isEmpty()) {
                cellStack.push(currentCell);
                int randomIndex = random.nextInt(0, notVisitedNeighbours.size());
                Cell randomNeighbour = notVisitedNeighbours.get(randomIndex);
                if (currentCell.getRow() + 2 == randomNeighbour.getRow()) {
                    surface[currentCell.getRow() + 1][currentCell.getColumn()].setCellType(CellType.ROAD);
                }
                if (currentCell.getRow() - 2 == randomNeighbour.getRow()) {
                    surface[currentCell.getRow() - 1][currentCell.getColumn()].setCellType(CellType.ROAD);
                }
                if (currentCell.getColumn() + 2 == randomNeighbour.getColumn()) {
                    surface[currentCell.getRow()][currentCell.getColumn() + 1].setCellType(CellType.ROAD);
                }
                if (currentCell.getColumn() - 2 == randomNeighbour.getColumn()) {
                    surface[currentCell.getRow()][currentCell.getColumn() - 1].setCellType(CellType.ROAD);
                }
                currentCell = randomNeighbour;
                visitedCells[randomNeighbour.getRow()][randomNeighbour.getColumn()] = true;
            } else {
                currentCell = cellStack.pop();
            }
        }
        return new Maze(width, height, surface);
    }

    private void initializeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.visitedCells = new boolean[height][width];
        this.surface = getSettedSurface(width, height);
        cellStack = new Stack<>();
        random = new Random();
    }

    private Cell[][] getSettedSurface(int width, int height) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    cells[i][j] = new Cell(i, j, CellType.ROAD);
                } else {
                    cells[i][j] = new Cell(i, j, CellType.WALL);
                    visitedCells[i][j] = true;
                }
            }
        }
        return cells;
    }

    private boolean allCellsVisited() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!visitedCells[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Cell> getNotVisitedNeighbours(int column, int row) {
        List<Cell> neighbours = new ArrayList<>();
        if (row - 2 >= 0 && !visitedCells[row - 2][column]) {
            neighbours.add(surface[row - 2][column]);
        }
        if (row + 2 < height && !visitedCells[row + 2][column]) {
            neighbours.add(surface[row + 2][column]);
        }
        if (column - 2 >= 0 && !visitedCells[row][column - 2]) {
            neighbours.add(surface[row][column - 2]);
        }
        if (column + 2 < width && !visitedCells[row][column + 2]) {
            neighbours.add(surface[row][column + 2]);
        }
        return neighbours;
    }

    private boolean checkInputData(int width, int height) {
        return width >= MIN_ALLOWED_SIZE_OF_SIDE && height >= MIN_ALLOWED_SIZE_OF_SIDE
            && width % 2 != 0 && height % 2 != 0;
    }
}

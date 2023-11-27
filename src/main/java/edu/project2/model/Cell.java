package edu.project2.model;

public class Cell {
    private final int row;
    private final int column;
    private CellType cellType;

    public Cell(int row, int column, CellType cellType) {
        this.row = row;
        this.column = column;
        this.cellType = cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellType getCellType() {
        return cellType;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}

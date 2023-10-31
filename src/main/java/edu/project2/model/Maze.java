package edu.project2.model;

public class Maze {
    private final int width;
    private final int height;
    private final Cell[][] surface;

    public Maze(int width, int height, Cell[][] surface) {
        this.width = width;
        this.height = height;
        this.surface = surface;
    }

    public Cell[][] getSurface() {
        return surface;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

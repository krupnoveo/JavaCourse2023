package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public final Rectangle setWidth(int width) {
        return new Rectangle(width, height);
    }

    public final Rectangle setHeight(int height) {
        return new Rectangle(width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double area() {
        return width * height;
    }
}

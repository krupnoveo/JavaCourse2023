package edu.project4.models;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return p.x() >= x && p.x() < width + x && p.y() >= y && p.y() < height + y;
    }

    public Point getRandomPoint() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(x, width + x),
            ThreadLocalRandom.current().nextDouble(y, height + y)
        );
    }
}

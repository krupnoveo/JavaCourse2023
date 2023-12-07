package edu.project4.transformation;

import edu.project4.models.Point;

public class SinusTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = Math.sin(point.x());
        double newY = Math.sin(point.y());
        return new Point(newX, newY);
    }
}

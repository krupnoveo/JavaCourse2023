package edu.project4.transformation;

import edu.project4.models.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(point.x(), point.y());
    }
}

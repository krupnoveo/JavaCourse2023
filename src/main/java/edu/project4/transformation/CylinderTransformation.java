package edu.project4.transformation;

import edu.project4.models.Point;

public class CylinderTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());
        return new Point(x, point.y());
    }
}

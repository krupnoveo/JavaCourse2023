package edu.project4.transformation;

import edu.project4.models.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = Math.atan(point.x() / point.y());
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double x = theta / Math.PI;
        double y = radius - 1;
        return new Point(x, y);
    }
}

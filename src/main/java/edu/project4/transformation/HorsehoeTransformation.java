package edu.project4.transformation;

import edu.project4.models.Point;

public class HorsehoeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double x = (1 / radius) * (point.x() - point.y()) * (point.x() + point.y());
        double y = (1 / radius) * 2 * point.x() * point.y();
        return new Point(x, y);
    }
}

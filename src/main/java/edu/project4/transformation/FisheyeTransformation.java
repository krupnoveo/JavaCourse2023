package edu.project4.transformation;

import edu.project4.models.Point;

public class FisheyeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double x = 2 / (radius + 1) * point.y();
        double y = 2 / (radius + 1) * point.x();
        return new Point(x, y);
    }
}

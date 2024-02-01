package edu.project4.transformation;

import edu.project4.models.Point;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double theta = Math.atan(point.x() / point.y());
        double x = radius * Math.sin(theta + radius);
        double y = radius * Math.cos(theta - radius);
        return new Point(x, y);
    }
}

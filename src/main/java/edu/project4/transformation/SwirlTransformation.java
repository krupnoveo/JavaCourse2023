package edu.project4.transformation;

import edu.project4.models.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double r = Math.pow(radius, 2);
        double x = point.x() * Math.sin(r) - point.y() * Math.cos(r);
        double y = point.x() * Math.cos(r) + point.y() * Math.sin(r);
        return new Point(x, y);
    }
}

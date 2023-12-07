package edu.project4.transformation;

import edu.project4.models.Point;

public class BubbleTransformation implements Transformation {
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Point apply(Point point) {
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double x = 4 / ((radius * radius) + 4) * point.x();
        double y = 4 / ((radius * radius) + 4) * point.y();
        return new Point(x, y);
    }
}

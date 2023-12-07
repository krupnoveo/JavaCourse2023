package edu.project4.transformation;

import edu.project4.models.Point;

public class ExTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double oldX = point.x();
        double oldY = point.y();
        double r = Math.sqrt(oldX * oldX + oldY * oldY);
        double theta = Math.atan(oldX / oldY);
        double p0 = Math.sin(theta + r);
        p0 = p0 * p0 * p0;
        double p1 = Math.cos(theta - r);
        p1 = p1 * p1 * p1;
        return new Point(r * (p0 + p1), r * (p0 - p1));
    }
}

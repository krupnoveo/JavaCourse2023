package edu.project4.transformation;

import edu.project4.models.Point;
import java.util.concurrent.ThreadLocalRandom;

public class HyperbolicTransformation implements Transformation {
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Point apply(Point point) {
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double theta = Math.atan(point.x() / point.y());
        double x = Math.sin(theta) / radius;
        double y;
        if (ThreadLocalRandom.current().nextBoolean()) {
            y = radius * Math.cos(theta);
        } else {
            y = radius * -Math.cos(theta);
        }
        return new Point(x, y);
    }
}

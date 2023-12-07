package edu.project4.render;

import edu.project4.models.AffineTransformationCoeffiecients;
import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.models.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

abstract public class AbstractRenderer implements Renderer {
    private static final int START_STEP = -20;

    protected void generateOneSample(
        FractalImage canvas,
        Rect world,
        int iterPerSample,
        AffineTransformationCoeffiecients[] transformationCoefficients,
        List<Transformation> variations,
        int symmetryAxes
    ) {
        Point pw = world.getRandomPoint();
        for (int step = START_STEP; step < iterPerSample; ++step) {
            AffineTransformationCoeffiecients coefficients = getRandomAffineCoefFromArray(transformationCoefficients);
            pw = applyAffineCoefficients(pw, coefficients);
            if (step >= 0) {
                Transformation variation = getRandomTransformation(variations);
                pw = variation.apply(pw);
                if (world.contains(pw)) {
                    double theta = 0.0;
                    double incr = 2 * Math.PI / symmetryAxes;
                    for (int axis = 0; axis < symmetryAxes; axis++) {
                        theta += incr;
                        Point pwr = getRotatedPoint(pw, theta);
                        if (!world.contains(pwr)) {
                            continue;
                        }
                        Pixel pixel = getPixel(canvas, pwr, world);
                        if (pixel != null) {
                            synchronized (pixel) {
                                setColorOnPixel(pixel, coefficients);
                            }
                        }
                    }
                }
            }
        }
    }

    protected Pixel getPixel(FractalImage canvas, Point p, Rect world) {
        return canvas.getPixel(
            (int) ((p.x() - world.x()) * canvas.resolution().width() / world.width()),
            (int) ((p.y() - world.y()) * canvas.resolution().height() / world.height())
        );
    }

    protected AffineTransformationCoeffiecients getRandomAffineCoefFromArray(
        AffineTransformationCoeffiecients[] coefficients
    ) {
        int randomAffineCoefs = ThreadLocalRandom.current().nextInt(0, coefficients.length);
        return coefficients[randomAffineCoefs];
    }

    protected Point applyAffineCoefficients(
        Point pw,
        AffineTransformationCoeffiecients coefficients
    ) {
        double x = coefficients.a() * pw.x() + coefficients.b() * pw.y() + coefficients.c();
        double y = coefficients.d() * pw.x() + coefficients.e() * pw.y() + coefficients.f();
        return new Point(x, y);
    }

    protected AffineTransformationCoeffiecients[] getArrayOfRandomAffineCoefficients(
        int samples
    ) {
        AffineTransformationCoeffiecients[] coefficients = new AffineTransformationCoeffiecients[samples];
        for (int i = 0; i < samples; i++) {
            coefficients[i] = AffineTransformationCoeffiecients.getRandomCoefficients();
        }
        return coefficients;
    }

    protected Transformation getRandomTransformation(
        List<Transformation> variations
    ) {
        return variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
    }

    protected Point getRotatedPoint(Point pw, double theta) {
        double xRot = pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta);
        double yRot = pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta);
        return new Point(xRot, yRot);
    }

    protected void setColorOnPixel(
        Pixel pixel,
        AffineTransformationCoeffiecients coefs
    ) {
        if (pixel.getHitCount() == 0) {
            pixel.setR(coefs.color().getRed());
            pixel.setG(coefs.color().getGreen());
            pixel.setB(coefs.color().getBlue());
        } else {
            pixel.setR((pixel.getR() + coefs.color().getRed()) / 2);
            pixel.setG((pixel.getG() + coefs.color().getGreen()) / 2);
            pixel.setB((pixel.getB() + coefs.color().getBlue()) / 2);
        }
        pixel.setHitCount(pixel.getHitCount() + 1);
    }
}

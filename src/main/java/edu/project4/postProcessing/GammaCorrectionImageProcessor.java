package edu.project4.postProcessing;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;

public class GammaCorrectionImageProcessor implements ImageProcessor {
    private final double gamma;

    public GammaCorrectionImageProcessor(double preferredGamma) {
        this.gamma = preferredGamma;
    }

    @Override
    public void process(FractalImage image) {
        double maxNormal = getMaxNormal(image);
        changeColorOfPixels(maxNormal, image);
    }

    private double getMaxNormal(FractalImage image) {
        double maxNormal = 0.0;
        for (int x = 0; x < image.resolution().width(); x++) {
            for (int y = 0; y < image.resolution().height(); y++) {
                Pixel pixel = image.getPixel(x, y);
                if (pixel != null) {
                    if (pixel.getHitCount() != 0) {
                        pixel.setNormal(Math.log10(pixel.getHitCount()));
                        if (pixel.getNormal() > maxNormal) {
                            maxNormal = pixel.getNormal();
                        }
                    }
                }
            }
        }
        return maxNormal;
    }

    private void changeColorOfPixels(double max, FractalImage image) {
        for (int x = 0; x < image.resolution().width(); x++) {
            for (int y = 0; y < image.resolution().height(); y++) {
                Pixel pixel = image.getPixel(x, y);
                if (pixel != null) {
                    double power = 1.0 / gamma;
                    pixel.setNormal(pixel.getNormal() / max);
                    pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormal(), power)));
                    pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormal(), power)));
                    pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormal(), power)));
                }
            }
        }
    }
}

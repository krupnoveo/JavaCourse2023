package edu.project4.imageProcessing;

import edu.project4.models.FractalImage;
import edu.project4.models.ImageFormat;
import edu.project4.models.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {}

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(
            image.resolution().width(),
            image.resolution().height(),
            BufferedImage.TYPE_INT_RGB
        );
        for (int x = 0; x < image.resolution().width(); x++) {
            for (int y = 0; y < image.resolution().height(); y++) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
        ImageIO.write(bufferedImage, format.name(), new File(filename.toString()));
    }
}

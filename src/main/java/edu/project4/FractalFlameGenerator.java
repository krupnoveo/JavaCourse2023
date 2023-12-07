package edu.project4;

import edu.project4.imageProcessing.ImageUtils;
import edu.project4.models.FractalImage;
import edu.project4.models.ImageFormat;
import edu.project4.models.Rect;
import edu.project4.models.Resolution;
import edu.project4.postProcessing.GammaCorrectionImageProcessor;
import edu.project4.postProcessing.ImageProcessor;
import edu.project4.render.MultiThreadRenderer;
import edu.project4.render.Renderer;
import edu.project4.transformation.Transformation;
import java.nio.file.Path;
import java.util.List;

public final class FractalFlameGenerator {
    private FractalFlameGenerator() {}

    @SuppressWarnings("checkstyle:ParameterNumber")
    public static boolean createFlame(
        Resolution resolution,
        Rect world,
        List<Transformation> transformationList,
        int symmetryAxisCount,
        double preferredGamma,
        int startPixelsCount,
        int producedPixelsForEachStartPixel,
        Path filename,
        ImageFormat generatedPictureFormat
    ) {
        FractalImage image = FractalImage.create(resolution);
        Renderer renderer = new MultiThreadRenderer();
        ImageProcessor processor = new GammaCorrectionImageProcessor(preferredGamma);
        renderer.render(
            image,
            world,
            transformationList,
            startPixelsCount,
            producedPixelsForEachStartPixel,
            symmetryAxisCount
        );
        processor.process(image);
        try {
            ImageUtils.save(image, filename, generatedPictureFormat);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

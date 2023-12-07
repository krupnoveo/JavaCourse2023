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
import edu.project4.transformation.BubbleTransformation;
import edu.project4.transformation.CylinderTransformation;
import edu.project4.transformation.ExTransformation;
import edu.project4.transformation.FisheyeTransformation;
import edu.project4.transformation.HandkerchiefTransformation;
import edu.project4.transformation.HeartTransformation;
import edu.project4.transformation.HorsehoeTransformation;
import edu.project4.transformation.HyperbolicTransformation;
import edu.project4.transformation.LinearTransformation;
import edu.project4.transformation.PolarTransformation;
import edu.project4.transformation.SinusTransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.transformation.TangentTransformation;
import edu.project4.transformation.Transformation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    public static void main(String[] args) {
        int counter = 22;
        List<Transformation> list = List.of(
            new BubbleTransformation(),
            new CylinderTransformation(),
            new ExTransformation(),
            new FisheyeTransformation(),
            new HandkerchiefTransformation(),
            new HeartTransformation(),
            new HorsehoeTransformation(),
            new HyperbolicTransformation(),
            new LinearTransformation(),
            new PolarTransformation(),
            new SinusTransformation(),
            new SphericalTransformation(),
            new SwirlTransformation(),
            new TangentTransformation()
        );
        while (true) {
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 16384);
            String temp = Integer.toBinaryString(randomNumber);
            String binary = "".repeat(14 - temp.length()) + temp;
            List<Transformation> list1 = new ArrayList<>();
            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '1') {
                    list1.add(list.get(i));
                }
            }
            System.out.println(FractalFlameGenerator.createFlame(
                new Resolution(7000, 4500),
                new Rect(-4, -3, 8, 6),
                list1,
                ThreadLocalRandom.current().nextInt(1, 7),
                1.5,
                100,
                5_000_000,
                Path.of("/Users/evgenij/Desktop/fractals/" + counter + ".bmp"),
                ImageFormat.BMP
            ));
            counter++;
        }
    }
}

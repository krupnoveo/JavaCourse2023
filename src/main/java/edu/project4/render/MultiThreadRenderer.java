package edu.project4.render;

import edu.project4.models.AffineTransformationCoeffiecients;
import edu.project4.models.FractalImage;
import edu.project4.models.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadRenderer extends AbstractRenderer {
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        CompletableFuture<Void>[] futures = new CompletableFuture[samples];
        AffineTransformationCoeffiecients[] transformationCoeffiecients = getArrayOfRandomAffineCoefficients(samples);
        for (int i = 0; i < samples; i++) {
            futures[i] = CompletableFuture.runAsync(() -> {
                generateOneSample(canvas, world, iterPerSample, transformationCoeffiecients, variations, symmetry);
            });
        }
        CompletableFuture.allOf(futures).join();
        service.shutdown();
        return canvas;
    }
}

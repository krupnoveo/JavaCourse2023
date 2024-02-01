package edu.project4.render;

import edu.project4.models.AffineTransformationCoeffiecients;
import edu.project4.models.FractalImage;
import edu.project4.models.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer {

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        AffineTransformationCoeffiecients[] transformationCoeffiecients = getArrayOfRandomAffineCoefficients(samples);
        for (int num = 0; num < samples; ++num) {
            generateOneSample(canvas, world, iterPerSample, transformationCoeffiecients, variations, symmetry);
        }
        return canvas;
    }
}

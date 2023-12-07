package edu.project4.render;

import edu.project4.models.FractalImage;
import edu.project4.models.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    );
}

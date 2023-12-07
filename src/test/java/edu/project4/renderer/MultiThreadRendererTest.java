package edu.project4.renderer;

import edu.project4.models.FractalImage;
import edu.project4.models.Rect;
import edu.project4.models.Resolution;
import edu.project4.render.MultiThreadRenderer;
import edu.project4.transformation.HeartTransformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MultiThreadRendererTest {
    @Test
    @DisplayName("Тест MultiThreadRenderer.render()")
    public void render_shouldWorkCorrectly() {
        assertDoesNotThrow(() -> new MultiThreadRenderer().render(
            FractalImage.create(new Resolution(1920, 1080)),
            new Rect(-4, -3, 8, 6),
            List.of(new HeartTransformation()),
            10,
            100_000,
            1
        ));
    }
}

package edu.project4;

import edu.project4.models.ImageFormat;
import edu.project4.models.Rect;
import edu.project4.models.Resolution;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalFlameGeneratorTest {
    @TempDir
    private Path tempdir;

    @Test
    @DisplayName("Тест FractalFlameGenerator.createFlame()")
    public void createFlame_shouldNotThrowException() {
        Path file = tempdir.resolve("1.png");
        boolean result;
        result = assertDoesNotThrow(() -> FractalFlameGenerator.createFlame(
            new Resolution(1920, 1080),
            new Rect(-4, -3, 8, 6),
            List.of(
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
            ),
            1,
            2.0,
            10,
            100_000,
            file,
            ImageFormat.PNG
        ));
        assertTrue(result);
        assertThat(tempdir.resolve("1.png")).exists();
    }
}

package edu.project4.imageProcessing;

import edu.project4.models.FractalImage;
import edu.project4.models.ImageFormat;
import edu.project4.models.Resolution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageUtilsTest {
    @TempDir
    private Path tempdir;

    @Test
    @DisplayName("Тест ImageUtils.save()")
    public void save_shouldWorkCorrectly() {
        FractalImage image = FractalImage.create(new Resolution(1920, 1080));
        try {
            ImageUtils.save(image, tempdir.resolve("1.png"), ImageFormat.PNG);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(tempdir.resolve("1.png")).exists();
    }
}

package edu.project4.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FractalImageTest {
    @Test
    @DisplayName("Тест FractalImage.create()")
    public void create_shouldWorkCorrectly() {
        FractalImage actual = FractalImage.create(new Resolution(1920, 1080));
        for (int i = 0; i < 1080; i++) {
            for (int j = 0; j < 1920; j++) {
                Pixel pixel = actual.data()[i][j];
                assertAll(
                    () -> assertThat(pixel).isEqualTo(new Pixel(0, 0, 0, 0, 0)),
                    () -> assertThat(actual.resolution().height()).isEqualTo(1080),
                    () -> assertThat(actual.resolution().width()).isEqualTo(1920)
                );
            }
        }
    }

    @Test
    @DisplayName("Тест FractalImage.contains()")
    public void contains_shouldReturnCorrectAnswer() {
        FractalImage image = FractalImage.create(new Resolution(1920, 1080));
        assertThat(image.contains(0, 1079)).isTrue();
    }

    @Test
    @DisplayName("Тест FractalImage.getPixel()")
    public void getPixel_shouldReturnCorrectAnswer() {
        FractalImage image = FractalImage.create(new Resolution(1920, 1080));
        assertThat(image.getPixel(1, 1)).isNotNull();
        assertThat(image.getPixel(-1, 2000)).isNull();
    }
}

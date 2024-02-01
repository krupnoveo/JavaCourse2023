package edu.project4.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RectTest {
    @Test
    @DisplayName("Тест Rect.contains()")
    public void contains_shouldWorkCorrectly() {
        Rect rect = new Rect(-4, -3, 8, 6);
        Point point = new Point(-3, 2);
        assertThat(rect.contains(point)).isTrue();
    }

    @Test
    @DisplayName("Тест Rect.getRandomPoint()")
    public void getRandomPoint_shouldReturnCorrectAnswer() {
        Rect rect = new Rect(-4, -3, 8, 6);
        Point p1 = rect.getRandomPoint();
        Point p2 = rect.getRandomPoint();
        assertThat(p1).isNotEqualTo(p2);
    }
}

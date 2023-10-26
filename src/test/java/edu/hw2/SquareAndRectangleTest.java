package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareAndRectangleTest {
    private static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }
    @ParameterizedTest
    @DisplayName("Тест на проверку принципа подстановки Барбары Лисков")
    @MethodSource("rectangles")
    public void rectangleArea(Rectangle rect) {
        Rectangle rectangle = rect.setHeight(20);
        rectangle = rectangle.setWidth(10);
        assertEquals(200.0, rectangle.area());
    }
    @Test
    @DisplayName("Тест Square.setSide()")
    public void setSide_shouldGiveSquareWithGivenSides() {
        Square square = new Square(2);
        square = square.setSide(5);
        assertEquals(square.area(), 25);
    }
}

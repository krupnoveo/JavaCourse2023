package edu.hw10.task1;

import edu.hw10.task1.modelForTest.ExampleModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RandomObjectGeneratorTest {
    @Test
    @DisplayName("Тест RandomObjectGenerator.genObject() с созданием класса через конструктор")
    public void genObject_constructor_shouldReturnObjectWithCorrectFields() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ExampleModel model = generator.genObject(ExampleModel.class);
        assertAll(
            () -> assertThat(model.getA()).isBetween((byte) 10, (byte) 12),
            () -> assertThat(model.getB()).isBetween((short) 10, (short) 12),
            () -> assertThat(model.getC()).isBetween(10, 12),
            () -> assertThat(model.getD()).isBetween(10L, 12L),
            () -> assertThat(model.getE()).isBetween(10f, 12f),
            () -> assertThat(model.getF()).isBetween(10d, 12d),
            () -> assertThat(model.isG()).isIn(true, false),
            () -> assertThat(model.getH()).isNotNull().isNotEmpty()
        );
    }

    @Test
    @DisplayName("Тест RandomObjectGenerator.genObject с созданием класса через фабричный метод")
    public void genObject_fabricMethod_shouldReturnObjectWithCorrectFields() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ExampleModel model = generator.genObject(ExampleModel.class, "create");
        assertAll(
            () -> assertThat(model.getA()).isBetween((byte) 10, (byte) 12),
            () -> assertThat(model.getB()).isBetween((short) 10, (short) 12),
            () -> assertThat(model.getC()).isBetween(10, 12),
            () -> assertThat(model.getD()).isBetween(10L, 12L),
            () -> assertThat(model.getE()).isBetween(10f, 12f),
            () -> assertThat(model.getF()).isBetween(10d, 12d),
            () -> assertThat(model.isG()).isIn(true, false),
            () -> assertThat(model.getH()).isNotNull().isNotEmpty()
        );
    }

    @Test
    @DisplayName("Тест RandomObjectGenerator.genObject() с выкидыванием исключения при создании через несуществующий фабричный метод")
    public void genObject_fabricMethod_shouldThrowIllegalArgumentException() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        assertThatThrownBy(() -> generator.genObject(ExampleModel.class, "getNew"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}

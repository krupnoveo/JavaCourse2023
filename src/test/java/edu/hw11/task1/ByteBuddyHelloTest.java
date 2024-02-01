package edu.hw11.task1;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyHelloTest {
    @SneakyThrows
    @Test
    @DisplayName("Тест ByteBuddyHello.toString()")
    public void toString_shouldReturnCorrectAnswer() {
        Class<?> aClass = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        assertEquals(
            "Hello, ByteBuddy!",
            aClass.getDeclaredConstructor().newInstance().toString()
        );
    }
}

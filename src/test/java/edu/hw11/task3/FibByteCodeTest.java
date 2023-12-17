package edu.hw11.task3;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import static org.assertj.core.api.Assertions.assertThat;

public class FibByteCodeTest {
    @SneakyThrows
    @Test
    @DisplayName("Тест динамического создания класса с методом для вычисления чисел фибоначчи")
    public void shouldCreateAndCountCorrectlyFibNumbers() {
        Class<?> fibClass = new ByteBuddy()
            .subclass(Object.class)
            .name("FibCalculator")
            .defineMethod("countFib", long.class, Visibility.PUBLIC)
            .withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                @NotNull
                public ByteCodeAppender appender(@NotNull Target target) {
                    return (mv, context, methodDescription) -> {
                        Label l1 = new Label();
                        mv.visitCode();
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);

                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        mv.visitLabel(l1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibCalculator", "countFib", "(I)J", false);

                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibCalculator", "countFib", "(I)J", false);
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitEnd();
                        return new ByteCodeAppender.Size(5, 2);
                    };
                }

                @Override
                @NotNull
                public InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        long actual = (long) fibClass.getDeclaredMethod("countFib", int.class)
            .invoke(fibClass.getDeclaredConstructor().newInstance(), 20);
        assertThat(actual).isEqualTo(6765);
    }
}

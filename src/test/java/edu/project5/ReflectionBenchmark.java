package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    record Student(String name, String surname) {
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaMetafactory;

    @Setup
    public void setup() throws NoSuchMethodException {
        student = new Student("Evgeniy", "Krupnov");
        //reflection
        method = student.getClass().getDeclaredMethod("name");

        //methodHandles
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(String.class);
        try {
            methodHandle = lookup.findVirtual(Student.class, "name", mt);
        } catch (NoSuchMethodException | IllegalAccessException | SecurityException | NullPointerException e) {
            throw new RuntimeException(e);
        }

        //lambdaMetafactory
        try {
            CallSite callSite = LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                MethodHandles.lookup().findVirtual(Student.class, "name", mt),
                MethodType.methodType(String.class, Student.class)
            );
            lambdaMetafactory = (Function<Student, String>) callSite.getTarget().invokeExact();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) {
        try {
            String name = (String) method.invoke(student);
            bh.consume(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void methodHandles(Blackhole bh) {
        try {
            String name = (String) methodHandle.invoke(student);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = lambdaMetafactory.apply(student);
        bh.consume(name);
    }
}

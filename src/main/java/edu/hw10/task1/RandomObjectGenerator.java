package edu.hw10.task1;

import edu.hw10.task1.parametersGenerator.BooleanGenerator;
import edu.hw10.task1.parametersGenerator.ByteGenerator;
import edu.hw10.task1.parametersGenerator.DoubleGenerator;
import edu.hw10.task1.parametersGenerator.FloatGenerator;
import edu.hw10.task1.parametersGenerator.Generator;
import edu.hw10.task1.parametersGenerator.IntGenerator;
import edu.hw10.task1.parametersGenerator.LongGenerator;
import edu.hw10.task1.parametersGenerator.ShortGenerator;
import edu.hw10.task1.parametersGenerator.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

public class RandomObjectGenerator {
    private static final Map<Class<?>, Generator<?>> GENERATOR_MAP = new HashMap<>();

    public RandomObjectGenerator() {
        GENERATOR_MAP.put(byte.class, new ByteGenerator());
        GENERATOR_MAP.put(short.class, new ShortGenerator());
        GENERATOR_MAP.put(int.class, new IntGenerator());
        GENERATOR_MAP.put(long.class, new LongGenerator());
        GENERATOR_MAP.put(float.class, new FloatGenerator());
        GENERATOR_MAP.put(double.class, new DoubleGenerator());
        GENERATOR_MAP.put(boolean.class, new BooleanGenerator());
        GENERATOR_MAP.put(String.class, new StringGenerator());
    }

    public <T> T genObject(Class<T> tClass, String fabricMethod) {
        return createClass(tClass, fabricMethod);
    }

    public <T> T genObject(Class<T> tClass) {
        return genObject(tClass, null);
    }

    @SneakyThrows
    private <T> T createClass(Class<T> tClass, String fabricMethod) {
        if (fabricMethod == null) {
            Constructor<T> constructor = getConstructor(tClass);
            Object[] params = getGeneratedRandomParameters(constructor.getParameters());
            return constructor.newInstance(params);
        }
        Method method = getFabricMethod(tClass, fabricMethod);
        if (method == null) {
            throw new IllegalArgumentException("No such method with name: " + fabricMethod);
        }
        Object[] params = getGeneratedRandomParameters(method.getParameters());
        return tClass.cast(method.invoke(null, params));
    }

    @SneakyThrows
    private <T> Constructor<T> getConstructor(Class<T> tClass) {
        Constructor<?> constructorWithMaxArgs;
        Constructor<?>[] constructors = tClass.getDeclaredConstructors();
        constructorWithMaxArgs = constructors[0];
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > constructorWithMaxArgs.getParameterCount()) {
                constructorWithMaxArgs = constructor;
            }
        }
        return (Constructor<T>) constructorWithMaxArgs;
    }

    private Object[] getGeneratedRandomParameters(Parameter[] parameters) {
        Object[] params = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            params[i] = GENERATOR_MAP.get(parameters[i].getType()).generate(parameters[i].getAnnotations());
        }
        return params;
    }

    private <T> Method getFabricMethod(Class<T> tClass, String fabricMethod) {
        Method[] methods = tClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(fabricMethod)) {
                return method;
            }
        }
        return null;
    }
}

package edu.hw10.task2;

import java.lang.reflect.Proxy;
import java.nio.file.Path;

public final class CacheProxy {

    private CacheProxy() {}

    public static <T> T create(T object, Class<T> tClass, Path pathForCaching) {
        return (T) Proxy.newProxyInstance(
            tClass.getClassLoader(),
            tClass.getInterfaces(),
            new CacheInvocationHandler(
                object,
                pathForCaching
            )
        );
    }
}

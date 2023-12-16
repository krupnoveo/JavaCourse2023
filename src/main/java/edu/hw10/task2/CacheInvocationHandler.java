package edu.hw10.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.hw10.task2.annotation.Cache;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;

public class CacheInvocationHandler implements InvocationHandler {
    private static final String EXTENSION_FOR_CACHE_FILES = ".cache";
    private static final String DELIMITER = ":";
    private final Gson gson = new GsonBuilder().create();
    private final Map<Method, Map<List<Object>, Object>> inMemoryCache;
    private final Object proxiedObject;
    private final Path pathForDiskCaching;

    public CacheInvocationHandler(
        Object proxiedObject,
        Path pathForDiskCaching
    ) {
        this.pathForDiskCaching = pathForDiskCaching;
        this.proxiedObject = proxiedObject;
        this.inMemoryCache = new HashMap<>();
    }

    @SneakyThrows
    @Override
    public Object invoke(
        Object proxy,
        Method method,
        Object[] args
    ) {
        if (method.isAnnotationPresent(Cache.class)) {
            if (method.getAnnotation(Cache.class).persist()) {
                return invokeAndCacheOnDisk(method, args);
            }
            return invokeAndCacheInMemory(method, args);
        }
        return method.invoke(proxiedObject, args);
    }

    @SneakyThrows
    private Object invokeAndCacheInMemory(
        Method method,
        Object[] args
    ) {
        var argumentsToList = Arrays.stream(args).toList();
        if (inMemoryCache.containsKey(method)) {
            var foundMethod = inMemoryCache.get(method);
            if (foundMethod.containsKey(argumentsToList)) {
                return foundMethod.get(argumentsToList);
            }
            foundMethod.put(
                argumentsToList,
                method.invoke(proxiedObject, args)
            );
            return inMemoryCache.get(method).get(argumentsToList);
        }
        inMemoryCache.put(method, new HashMap<>());
        var result = method.invoke(proxiedObject, args);
        inMemoryCache.get(method).put(
            argumentsToList,
            result
        );
        return result;
    }

    @SneakyThrows
    private Object invokeAndCacheOnDisk(Method method, Object[] args) {
        Path fileName = pathForDiskCaching.resolve(method.getName() + EXTENSION_FOR_CACHE_FILES);
        if (!Files.exists(fileName)) {
            Files.createFile(fileName);
            var result = method.invoke(proxiedObject, args);
            writeToCacheFile(args, result, fileName);
            return result;
        }
        return findResultInCacheFile(fileName, args, method);
    }

    @SneakyThrows
    private Object findResultInCacheFile(Path fileName, Object[] args, Method method) {
        List<String> lines = Files.readAllLines(fileName);
        for (String string : lines) {
            var line = string.split(DELIMITER);
            if (gson.toJson(args).equals(line[0])) {
                return gson.fromJson(line[1], method.getReturnType());
            }
        }
        var result = method.invoke(proxiedObject, args);
        writeToCacheFile(args, result, fileName);
        return result;
    }

    @SneakyThrows
    private void writeToCacheFile(Object[] args, Object result, Path fileName) {
        String argsInJson = gson.toJson(args);
        String resultInJson = gson.toJson(result);
        Files.writeString(fileName, argsInJson + DELIMITER + resultInJson + "\n", StandardOpenOption.APPEND);
    }
}

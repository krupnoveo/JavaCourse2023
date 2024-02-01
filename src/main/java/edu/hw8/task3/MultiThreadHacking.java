package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;

public class MultiThreadHacking extends DatabaseHacking {
    private final ConcurrentMap<String, String> encodedDatabase;
    private final ConcurrentMap<String, String> decodedDatabase;

    public MultiThreadHacking(ConcurrentMap<String, String> encodedDatabase) {
        this.encodedDatabase = encodedDatabase;
        this.decodedDatabase = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public ConcurrentMap<String, String> decodeDatabase() {
        int threadsCount = 10;
        long step = ((TOP_MASK_BORDER - BOTTOM_MASK_BORDER) / threadsCount);
        long start = BOTTOM_MASK_BORDER;
        List<Long> intervals = new ArrayList<>();
        intervals.add(start);
        for (int i = 0; i < threadsCount; i++) {
            start += step;
            intervals.add(start);
        }
        ExecutorService service = Executors.newFixedThreadPool(threadsCount);
        List<Character> generatedPassword = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            generatedPassword.add(ALLOWED_SYMBOLS[0]);
            checkPassword(generatedPassword);
        }
        CompletableFuture<Void>[] futures = new CompletableFuture[threadsCount];
        for (int j = 0; j < threadsCount; j++) {
            final int finalJ = j;
            futures[j] = CompletableFuture.runAsync(() -> {
                for (long i = intervals.get(finalJ); i < intervals.get(finalJ + 1); i++) {
                    if (encodedDatabase.isEmpty()) {
                        break;
                    }
                    List<Character> generatedPass = new ArrayList<>();
                    long temp = i;
                    while (temp > 0) {
                        generatedPass.add(ALLOWED_SYMBOLS[(int) (temp % ALLOWED_SYMBOLS.length)]);
                        temp /= ALLOWED_SYMBOLS.length;
                    }
                    checkPassword(generatedPass);
                }
            }, service);
        }
        CompletableFuture.allOf(futures).join();
        service.shutdown();
        return decodedDatabase;
    }

    private void checkPassword(List<Character> password) {
        String pass = password.stream()
            .map(Object::toString)
            .collect(Collectors.joining());
        String md5Hash = DigestUtils.md5Hex(pass);
        if (encodedDatabase.containsKey(md5Hash)) {
            decodedDatabase.put(encodedDatabase.get(md5Hash), pass);
            encodedDatabase.remove(md5Hash);
        }
    }
}

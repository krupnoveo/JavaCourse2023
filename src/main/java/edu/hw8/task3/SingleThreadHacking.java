package edu.hw8.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;


public class SingleThreadHacking extends DatabaseHacking {
    private final Map<String, String> encodedDatabase;
    private final Map<String, String> decodedDatabase;

    public SingleThreadHacking(Map<String, String> encodedDatabase) {
        this.encodedDatabase = encodedDatabase;
        this.decodedDatabase = new HashMap<>();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public Map<String, String> decodeDatabase() {
        List<Character> generatedPassword = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            generatedPassword.add(ALLOWED_SYMBOLS[0]);
            checkPassword(generatedPassword);
        }
        for (long i = BOTTOM_MASK_BORDER; i < TOP_MASK_BORDER; i++) {
            if (encodedDatabase.isEmpty()) {
                break;
            }
            generatedPassword = new ArrayList<>();
            long temp = i;
            while (temp > 0) {
                generatedPassword.add(ALLOWED_SYMBOLS[(int) (temp % ALLOWED_SYMBOLS.length)]);
                temp /= ALLOWED_SYMBOLS.length;
            }
            checkPassword(generatedPassword);
        }
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

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("pass"));
    }
}

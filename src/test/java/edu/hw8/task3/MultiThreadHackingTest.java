package edu.hw8.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadHackingTest {
    public static Stream<Arguments> genCorrectInputData() {
        return Stream.of(
            Arguments.of(
                new ConcurrentHashMap<String, String>() {{
                    put("81dc9bdb52d04dc20036dbd8313ed055", "user1");
                    put("1a1dc91c907325c69271ddf0c944bc72", "user2");
                }},
                new ConcurrentHashMap<String, String>() {{
                    put("user1", "1234");
                    put("user2", "pass");
                }}
            )
        );
    }


    @ParameterizedTest
    @MethodSource("genCorrectInputData")
    @DisplayName("Тест MultiThreadHacking.decodeDatabase()")
    public void decodeDatabase_fromMultiThreadHacking_shouldWorkCorrectly(ConcurrentMap<String, String> encodedDatabase, ConcurrentMap<String, String> decodedDatabase) {
        ConcurrentMap<String, String> actual = new MultiThreadHacking(encodedDatabase).decodeDatabase();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(decodedDatabase);
    }
}

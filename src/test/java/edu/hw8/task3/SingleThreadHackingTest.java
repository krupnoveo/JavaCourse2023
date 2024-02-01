package edu.hw8.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class SingleThreadHackingTest {
    public static Stream<Arguments> genCorrectInputData() {
        return Stream.of(
            Arguments.of(
                new HashMap<String, String>() {{
                    put("81dc9bdb52d04dc20036dbd8313ed055", "user1");
                    put("1a1dc91c907325c69271ddf0c944bc72", "user2");
                }},
                new HashMap<String, String>() {{
                    put("user1", "1234");
                    put("user2", "pass");
                }}
            )
        );
    }


    @ParameterizedTest
    @MethodSource("genCorrectInputData")
    @DisplayName("Тест SingleThreadHacking.decodeDatabase()")
    public void decodeDatabase_fromSingleThreadHacking_shouldWorkCorrectly(Map<String, String> encodedDatabase, Map<String, String> decodedDatabase) {
        Map<String, String> actual = new SingleThreadHacking(encodedDatabase).decodeDatabase();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(decodedDatabase);
    }
}

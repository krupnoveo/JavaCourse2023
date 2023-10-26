package edu.hw3.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparatorForTreeMapTest {
    @Test
    @DisplayName("Тест ComparatorForTreeMap.compare()")
    public void compare_shouldSortCorrectly() {
        TreeMap<Integer, Integer> map = new TreeMap<>(new ComparatorForTreeMap<>());
        map.put(3, 1);
        map.put(null, 2);
        map.put(2, 3);
        assertThat(map.containsKey(null)).isTrue();
        assertThat(map.pollFirstEntry().getKey()).isEqualTo(null);
    }
}
